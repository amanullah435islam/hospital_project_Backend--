package com.hospital.pharmacy.service;

import com.hospital.common.exception.BadRequestException;
import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.medicine.entity.Medicine;
import com.hospital.medicine.repository.MedicineRepository;
import com.hospital.pharmacy.dto.StockIssueBatchResponse;
import com.hospital.pharmacy.dto.StockIssueRequest;
import com.hospital.pharmacy.dto.StockIssueResponse;
import com.hospital.pharmacy.entity.MedicineBatch;
import com.hospital.pharmacy.entity.StockTransaction;
import com.hospital.pharmacy.entity.StockTransactionType;
import com.hospital.pharmacy.repository.MedicineBatchRepository;
import com.hospital.pharmacy.repository.StockTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final MedicineRepository medicineRepository;

    private final MedicineBatchRepository
            medicineBatchRepository;

    private final StockTransactionRepository
            stockTransactionRepository;


    // ==========================================
    // ISSUE STOCK
    // ==========================================

    @Override
    @Transactional
    public StockIssueResponse issueStock(
            StockIssueRequest request
    ) {

        // --------------------------------------
        // FIND MEDICINE
        // --------------------------------------

        Medicine medicine =
                medicineRepository
                        .findById(
                                request.getMedicineId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Medicine not found with id: "
                                                + request.getMedicineId()
                                )
                        );


        // --------------------------------------
        // FIND AVAILABLE BATCHES
        // FEFO
        // --------------------------------------

        List<MedicineBatch> batches =
                medicineBatchRepository
                        .findByMedicineIdAndQuantityGreaterThanAndExpiryDateAfterOrderByExpiryDateAsc(
                                medicine.getId(),
                                0,
                                LocalDate.now()
                        );


        // --------------------------------------
        // CALCULATE TOTAL STOCK
        // --------------------------------------

        int totalAvailableStock =
                batches.stream()
                        .mapToInt(
                                MedicineBatch::getQuantity
                        )
                        .sum();


        // --------------------------------------
        // INSUFFICIENT STOCK
        // --------------------------------------

        if (
                totalAvailableStock
                        < request.getQuantity()
        ) {

            throw new BadRequestException(
                    "Insufficient stock for medicine: "
                            + medicine.getName()
                            + ". Available: "
                            + totalAvailableStock
                            + ", Requested: "
                            + request.getQuantity()
            );
        }


        // --------------------------------------
        // FEFO PROCESS
        // --------------------------------------

        int remainingToIssue =
                request.getQuantity();


        List<StockIssueBatchResponse>
                issuedBatches =
                new ArrayList<>();


        for (MedicineBatch batch : batches) {

            if (remainingToIssue <= 0) {
                break;
            }


            int availableQuantity =
                    batch.getQuantity();


            int issueQuantity =
                    Math.min(
                            availableQuantity,
                            remainingToIssue
                    );


            // ----------------------------------
            // UPDATE BATCH STOCK
            // ----------------------------------

            int remainingQuantity =
                    availableQuantity
                            - issueQuantity;

            batch.setQuantity(
                    remainingQuantity
            );

            medicineBatchRepository.save(
                    batch
            );


            // ----------------------------------
            // CREATE STOCK OUT TRANSACTION
            // ----------------------------------

            StockTransaction transaction =
                    new StockTransaction();

            transaction.setBatch(
                    batch
            );

            transaction.setTransactionType(
                    StockTransactionType.STOCK_OUT
            );

            transaction.setQuantity(
                    issueQuantity
            );

            transaction.setTransactionDate(
                    LocalDateTime.now()
            );

            transaction.setReason(
                    buildReason(request)
            );

            stockTransactionRepository.save(
                    transaction
            );


            // ----------------------------------
            // RESPONSE
            // ----------------------------------

            issuedBatches.add(
                    StockIssueBatchResponse
                            .builder()
                            .batchId(
                                    batch.getId()
                            )
                            .batchNumber(
                                    batch.getBatchNumber()
                            )
                            .quantityIssued(
                                    issueQuantity
                            )
                            .remainingQuantity(
                                    remainingQuantity
                            )
                            .sellingPrice(
                                    batch.getSellingPrice()
                            )
                            .build()
            );


            remainingToIssue -=
                    issueQuantity;
        }


        return StockIssueResponse
                .builder()
                .medicineId(
                        medicine.getId()
                )
                .medicineName(
                        medicine.getName()
                )
                .requestedQuantity(
                        request.getQuantity()
                )
                .issuedQuantity(
                        request.getQuantity()
                                - remainingToIssue
                )
                .batches(
                        issuedBatches
                )
                .build();
    }


    // ==========================================
    // BUILD REASON
    // ==========================================

    private String buildReason(
            StockIssueRequest request
    ) {

        if (
                request.getReason() != null
                        &&
                        !request.getReason()
                                .isBlank()
        ) {

            return request.getReason();
        }


        if (
                request.getReferenceType() != null
                        &&
                        request.getReferenceId() != null
        ) {

            return request.getReferenceType()
                    + " #"
                    + request.getReferenceId();
        }


        return "Stock issued";
    }
}
