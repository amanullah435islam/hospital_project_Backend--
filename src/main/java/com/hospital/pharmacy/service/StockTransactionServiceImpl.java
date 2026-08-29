package com.hospital.pharmacy.service;

import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.pharmacy.dto.StockTransactionResponse;
import com.hospital.pharmacy.entity.StockTransaction;
import com.hospital.pharmacy.repository.MedicineBatchRepository;
import com.hospital.pharmacy.repository.StockTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockTransactionServiceImpl
        implements StockTransactionService {

    private final StockTransactionRepository
            stockTransactionRepository;

    private final MedicineBatchRepository
            medicineBatchRepository;


    // ==========================================
    // GET TRANSACTIONS BY BATCH
    // ==========================================

    @Override
    public List<StockTransactionResponse>
    getTransactionsByBatch(Long batchId) {

        // --------------------------------------
        // CHECK BATCH EXISTS
        // --------------------------------------

        if (!medicineBatchRepository.existsById(batchId)) {

            throw new ResourceNotFoundException(
                    "Medicine batch not found with id: "
                            + batchId
            );
        }


        // --------------------------------------
        // FIND TRANSACTIONS
        // --------------------------------------

        return stockTransactionRepository
                .findByBatchIdOrderByTransactionDateDesc(
                        batchId
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // ==========================================
    // MAPPER
    // ==========================================

    private StockTransactionResponse mapToResponse(
            StockTransaction transaction
    ) {

        var batch =
                transaction.getBatch();

        var medicine =
                batch.getMedicine();

        return StockTransactionResponse
                .builder()
                .id(
                        transaction.getId()
                )
                .batchId(
                        batch.getId()
                )
                .batchNumber(
                        batch.getBatchNumber()
                )
                .medicineId(
                        medicine.getId()
                )
                .medicineName(
                        medicine.getName()
                )
                .transactionType(
                        transaction.getTransactionType()
                )
                .quantity(
                        transaction.getQuantity()
                )
                .transactionDate(
                        transaction.getTransactionDate()
                )
                .reason(
                        transaction.getReason()
                )
                .build();
    }
}
