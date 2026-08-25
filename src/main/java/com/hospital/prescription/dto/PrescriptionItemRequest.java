package com.hospital.prescription.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrescriptionItemRequest {

    @NotNull(message = "Medicine ID is required")
    private Long medicineId;

    @NotBlank(message = "Dosage is required")
    @Size(max = 100)
    private String dosage;

    @NotBlank(message = "Frequency is required")
    @Size(max = 100)
    private String frequency;

    @NotBlank(message = "Duration is required")
    @Size(max = 100)
    private String duration;

    @Size(max = 50)
    private String route;

    @Size(max = 300)
    private String instructions;
}
