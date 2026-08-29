package com.hospital.pharmacy.dto;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class MedicineBatchResponse {

    private Long id;

    private Long medicineId;

    private String medicineName;

    private String batchNumber;

    private BigDecimal purchasePrice;

    private BigDecimal sellingPrice;

    private Integer quantity;

    private LocalDate expiryDate;
}