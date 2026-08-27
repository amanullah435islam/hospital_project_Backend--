package com.hospital.billing.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class InvoiceCreateRequest {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotEmpty(
            message = "Invoice must contain at least one item"
    )
    @Valid
    private List<InvoiceItemRequest> items;

    @DecimalMin(
            value = "0.00",
            message = "Discount cannot be negative"
    )
    private BigDecimal discount;

    @DecimalMin(
            value = "0.00",
            message = "Tax cannot be negative"
    )
    private BigDecimal tax;

    @Size(max = 1000)
    private String notes;
}
