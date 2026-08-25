package com.hospital.medicine.dto;

import com.hospital.medicine.entity.MedicineStatus;
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

    @Size(max = 50)
    private String strength;

    @Size(max = 50)
    private String form;

    private MedicineStatus status;
}
