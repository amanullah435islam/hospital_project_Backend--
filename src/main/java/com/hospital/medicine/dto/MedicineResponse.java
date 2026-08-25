package com.hospital.medicine.dto;

import com.hospital.medicine.entity.MedicineStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MedicineResponse {

    private Long id;

    private String medicineCode;

    private String name;

    private String genericName;

    private String strength;

    private String form;

    private MedicineStatus status;
}
