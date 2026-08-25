package com.hospital.prescription.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PrescriptionCreateRequest {

    @NotNull(message = "Medical record ID is required")
    private Long medicalRecordId;

    @NotEmpty(
            message = "Prescription must contain at least one medicine"
    )
    @Valid
    private List<PrescriptionItemRequest> items;

    @Size(max = 2000)
    private String notes;
}
