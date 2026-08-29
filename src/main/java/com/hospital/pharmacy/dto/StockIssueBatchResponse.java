package com.hospital.pharmacy.dto;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@Builder
public class StockIssueBatchResponse {

    private Long batchId;

    private String batchNumber;

    private Integer quantityIssued;

    private Integer remainingQuantity;

    private BigDecimal sellingPrice;
}