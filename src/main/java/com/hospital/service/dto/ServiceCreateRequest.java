package com.hospital.service.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ServiceCreateRequest {

    @NotBlank(
            message = "Service name is required"
    )
    @Size(max = 150)
    private String name;

    @Size(max = 500)
    private String description;

    @NotNull(
            message = "Default price is required"
    )
    @DecimalMin(
            value = "0.00",
            message = "Price cannot be negative"
    )
    private BigDecimal defaultPrice;
}
