package com.hospital.billing.dto;

import com.hospital.billing.entity.InvoiceStatus;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class InvoiceResponse {

    private Long id;

    private String invoiceNumber;

    private Long patientId;

    private String patientCode;

    private String patientName;

    private LocalDate invoiceDate;

    private InvoiceStatus status;

    private BigDecimal subtotal;

    private BigDecimal discount;

    private BigDecimal tax;

    private BigDecimal grandTotal;

    private BigDecimal paidAmount;

    private BigDecimal dueAmount;

    private String notes;

    private List<InvoiceItemResponse> items;
}
