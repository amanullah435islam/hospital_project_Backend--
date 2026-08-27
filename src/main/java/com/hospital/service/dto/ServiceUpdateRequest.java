package com.hospital.service.dto;

import com.hospital.service.entity.ServiceStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ServiceUpdateRequest {

    @Size(max = 150)
    private String name;

    @Size(max = 500)
    private String description;

    @DecimalMin("0.00")
    private BigDecimal defaultPrice;

    private ServiceStatus status;
}