package com.hospital.pharmacy.dto;

import com.hospital.pharmacy.entity.StockTransactionType;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class StockTransactionResponse {

    private Long id;

    private Long batchId;

    private String batchNumber;

    private Long medicineId;

    private String medicineName;

    private StockTransactionType transactionType;

    private Integer quantity;

    private LocalDateTime transactionDate;

    private String reason;
}