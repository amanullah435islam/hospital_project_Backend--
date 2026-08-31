package com.hospital.pharmacy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DispensePrescriptionRequest {

    @NotNull(message = "Prescription ID is required")
    private Long prescriptionId;
}
