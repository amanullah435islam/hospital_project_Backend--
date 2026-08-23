package com.hospital.doctor.dto;

import com.hospital.doctor.entity.DoctorStatus;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@Builder
public class DoctorResponse {

    private Long id;

    private String doctorCode;

    private Long userId;

    private String doctorName;

    private String email;

    private Long departmentId;

    private String departmentName;

    private String specialization;

    private String qualification;

    private String licenseNumber;

    private Integer experienceYears;

    private BigDecimal consultationFee;

    private DoctorStatus status;
}
