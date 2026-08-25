package com.hospital.prescription.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class PrescriptionResponse {

    private Long id;

    private String prescriptionCode;

    private Long medicalRecordId;

    private Long patientId;

    private String patientCode;

    private String patientName;

    private Long doctorId;

    private String doctorCode;

    private String doctorName;

    private LocalDate prescribedDate;

    private String notes;

    private List<PrescriptionItemResponse> items;
}
