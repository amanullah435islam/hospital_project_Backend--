package com.hospital.pharmacy.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DispensePrescriptionResponse {

    private Long prescriptionId;

    private String status;

    private List<DispensedMedicineResponse> medicines;
}
