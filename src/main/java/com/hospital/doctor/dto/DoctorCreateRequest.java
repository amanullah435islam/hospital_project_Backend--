package com.hospital.doctor.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class DoctorCreateRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    @NotBlank(message = "Doctor code is required")
    @Size(max = 30)
    private String doctorCode;

    @NotBlank(message = "Specialization is required")
    @Size(max = 100)
    private String specialization;

    @Size(max = 255)
    private String qualification;

    @NotBlank(message = "License number is required")
    private String licenseNumber;

    @Min(
            value = 0,
            message = "Experience cannot be negative"
    )
    private Integer experienceYears;

    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "Consultation fee cannot be negative"
    )
    private BigDecimal consultationFee;
}
