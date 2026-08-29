package com.hospital.pharmacy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineCreateRequest {

    @NotBlank(message = "Medicine name is required")
    @Size(max = 150)
    private String name;

    @Size(max = 150)
    private String genericName;

    @Size(max = 100)
    private String strength;

    @Size(max = 100)
    private String dosageForm;
}