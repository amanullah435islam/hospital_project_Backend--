package com.hospital.pharmacy.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class StockIssueResponse {

    private Long medicineId;

    private String medicineName;

    private Integer requestedQuantity;

    private Integer issuedQuantity;

    private List<StockIssueBatchResponse> batches;
}
