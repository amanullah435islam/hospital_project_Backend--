package com.hospital.billing.dto;

import com.hospital.billing.entity.PaymentMethod;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class PaymentResponse {

    private Long id;

    private String paymentReference;

    private Long invoiceId;

    private String invoiceNumber;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private LocalDateTime paymentDate;

    private String notes;
}
