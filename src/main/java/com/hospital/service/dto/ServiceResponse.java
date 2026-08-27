package com.hospital.service.dto;

import com.hospital.service.entity.ServiceStatus;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@Builder
public class ServiceResponse {

    private Long id;

    private String serviceCode;

    private String name;

    private String description;

    private BigDecimal defaultPrice;

    private ServiceStatus status;
}