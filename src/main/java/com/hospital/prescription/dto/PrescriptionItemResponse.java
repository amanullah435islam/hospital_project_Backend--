package com.hospital.prescription.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PrescriptionItemResponse {

    private Long id;

    private Long medicineId;

    private String medicineCode;

    private String medicineName;

    private String genericName;

    private String strength;

    private String form;

    private String dosage;

    private String frequency;

    private String duration;

    private String route;

    private String instructions;
}
