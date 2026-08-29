package com.hospital.pharmacy.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class MedicineBatchCreateRequest {

    @NotNull(message = "Medicine ID is required")
    private Long medicineId;

    @NotBlank(message = "Batch number is required")
    private String batchNumber;

    @NotNull(message = "Purchase price is required")
    @DecimalMin("0.00")
    private BigDecimal purchasePrice;

    @NotNull(message = "Selling price is required")
    @DecimalMin("0.00")
    private BigDecimal sellingPrice;

    @NotNull(message = "Quantity is required")
    @Min(1)
    private Integer quantity;

    @NotNull(message = "Expiry date is required")
    @Future(message = "Expiry date must be in the future")
    private LocalDate expiryDate;
}