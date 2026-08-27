package com.hospital.billing.dto;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@Builder
public class InvoiceItemResponse {

    private Long id;

    private Long serviceId;

    private String serviceCode;

    private String serviceName;

    private String description;

    private BigDecimal unitPrice;

    private Integer quantity;

    private BigDecimal total;
}
