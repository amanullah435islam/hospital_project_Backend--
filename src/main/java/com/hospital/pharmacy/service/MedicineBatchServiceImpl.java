package com.hospital.pharmacy.service;

import com.hospital.common.exception.BadRequestException;
import com.hospital.common.exception.DuplicateResourceException;
import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.pharmacy.dto.MedicineBatchCreateRequest;
import com.hospital.pharmacy.dto.MedicineBatchResponse;
import com.hospital.pharmacy.entity.Medicine;
import com.hospital.pharmacy.entity.MedicineBatch;
import com.hospital.pharmacy.entity.MedicineStatus;
import com.hospital.pharmacy.entity.StockTransaction;
import com.hospital.pharmacy.entity.StockTransactionType;
import com.hospital.pharmacy.repository.MedicineBatchRepository;
import com.hospital.pharmacy.repository.MedicineRepository;
import com.hospital.pharmacy.repository.StockTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicineBatchServiceImpl
        implements MedicineBatchService {

    private final MedicineRepository medicineRepository;

    private final MedicineBatchRepository
            medicineBatchRepository;

    private final StockTransactionRepository
            stockTransactionRepository;


    // ==========================================
    // CREATE BATCH
    // ==========================================

    @Override
    public MedicineBatchResponse createBatch(
            MedicineBatchCreateRequest request
    ) {

        // --------------------------------------
        // FIND MEDICINE
        // --------------------------------------

        Medicine medicine =
                medicineRepository.findById(
                        request.getMedicineId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Medicine not found with id: "
                                        + request.getMedicineId()
                        )
                );


        // --------------------------------------
        // MEDICINE MUST BE ACTIVE
        // --------------------------------------

        if (
                medicine.getStatus()
                        != MedicineStatus.ACTIVE
        ) {

            throw new BadRequestException(
                    "Cannot create batch for inactive medicine: "
                            + medicine.getName()
            );
        }


        // --------------------------------------
        // DUPLICATE BATCH CHECK
        // --------------------------------------

        String batchNumber =
                request.getBatchNumber()
                        .trim();


        if (
                medicineBatchRepository
                        .existsByMedicineIdAndBatchNumber(
                                medicine.getId(),
                                batchNumber
                        )
        ) {

            throw new DuplicateResourceException(
                    "Batch already exists for medicine: "
                            + batchNumber
            );
        }


        // --------------------------------------
        // CREATE BATCH
        // --------------------------------------

        MedicineBatch batch =
                new MedicineBatch();

        batch.setMedicine(
                medicine
        );

        batch.setBatchNumber(
                batchNumber
        );

        batch.setPurchasePrice(
                request.getPurchasePrice()
        );

        batch.setSellingPrice(
                request.getSellingPrice()
        );

        batch.setQuantity(
                request.getQuantity()
        );

        batch.setExpiryDate(
                request.getExpiryDate()
        );


        MedicineBatch savedBatch =
                medicineBatchRepository.save(
                        batch
                );


        // --------------------------------------
        // CREATE STOCK-IN TRANSACTION
        // --------------------------------------

        StockTransaction transaction =
                new StockTransaction();

        transaction.setBatch(
                savedBatch
        );

        transaction.setTransactionType(
                StockTransactionType.STOCK_IN
        );

        transaction.setQuantity(
                request.getQuantity()
        );

        transaction.setTransactionDate(
                LocalDateTime.now()
        );

        transaction.setReason(
                "Initial stock for batch "
                        + batchNumber
        );


        stockTransactionRepository.save(
                transaction
        );


        return mapToResponse(
                savedBatch
        );
    }


    // ==========================================
    // GET BY ID
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public MedicineBatchResponse getBatchById(
            Long id
    ) {

        MedicineBatch batch =
                findBatch(id);

        return mapToResponse(batch);
    }


    // ==========================================
    // GET BY MEDICINE
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public List<MedicineBatchResponse>
    getBatchesByMedicine(
            Long medicineId
    ) {

        if (
                !medicineRepository
                        .existsById(medicineId)
        ) {

            throw new ResourceNotFoundException(
                    "Medicine not found with id: "
                            + medicineId
            );
        }


        return medicineBatchRepository
                .findByMedicineIdOrderByExpiryDateAsc(
                        medicineId
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // ==========================================
    // AVAILABLE BATCHES
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public List<MedicineBatchResponse>
    getAvailableBatches() {

        return medicineBatchRepository
                .findByQuantityGreaterThanAndExpiryDateAfterOrderByExpiryDateAsc(
                        0,
                        LocalDate.now()
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // ==========================================
    // FIND BATCH
    // ==========================================

    private MedicineBatch findBatch(
            Long id
    ) {

        return medicineBatchRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Medicine batch not found with id: "
                                        + id
                        )
                );
    }


    // ==========================================
    // MAPPER
    // ==========================================

    private MedicineBatchResponse mapToResponse(
            MedicineBatch batch
    ) {

        return MedicineBatchResponse
                .builder()
                .id(
                        batch.getId()
                )
                .medicineId(
                        batch.getMedicine()
                                .getId()
                )
                .medicineName(
                        batch.getMedicine()
                                .getName()
                )
                .batchNumber(
                        batch.getBatchNumber()
                )
                .purchasePrice(
                        batch.getPurchasePrice()
                )
                .sellingPrice(
                        batch.getSellingPrice()
                )
                .quantity(
                        batch.getQuantity()
                )
                .expiryDate(
                        batch.getExpiryDate()
                )
                .build();
    }
}
