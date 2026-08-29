package com.hospital.pharmacy.dto;

import com.hospital.pharmacy.entity.MedicineStatus;
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

    private String dosageForm;

    private MedicineStatus status;
}
