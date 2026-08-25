package com.hospital.billing.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class InvoiceItemRequest {

    @NotBlank(message = "Description is required")
    @Size(max = 200)
    private String description;

    @NotNull(message = "Unit price is required")
    @DecimalMin(
            value = "0.00",
            message = "Unit price cannot be negative"
    )
    private BigDecimal unitPrice;

    @NotNull(message = "Quantity is required")
    @Min(
            value = 1,
            message = "Quantity must be at least 1"
    )
    private Integer quantity;
}
