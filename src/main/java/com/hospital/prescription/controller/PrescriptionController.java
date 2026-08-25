package com.hospital.prescription.controller;

import com.hospital.common.response.ApiResponse;
import com.hospital.prescription.dto.PrescriptionCreateRequest;
import com.hospital.prescription.dto.PrescriptionResponse;
import com.hospital.prescription.service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;


    @PostMapping
    public ResponseEntity<
            ApiResponse<PrescriptionResponse>
            > createPrescription(

            @Valid
            @RequestBody
            PrescriptionCreateRequest request
    ) {

        PrescriptionResponse response =
                prescriptionService
                        .createPrescription(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Prescription created successfully",
                                response
                        )
                );
    }


    @GetMapping("/{id}")
    public ResponseEntity<
            ApiResponse<PrescriptionResponse>
            > getPrescription(
            @PathVariable Long id
    ) {

        PrescriptionResponse response =
                prescriptionService
                        .getPrescriptionById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Prescription retrieved successfully",
                        response
                )
        );
    }


    @GetMapping("/medical-record/{medicalRecordId}")
    public ResponseEntity<
            ApiResponse<PrescriptionResponse>
            > getByMedicalRecord(
            @PathVariable Long medicalRecordId
    ) {

        PrescriptionResponse response =
                prescriptionService
                        .getByMedicalRecordId(
                                medicalRecordId
                        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Prescription retrieved successfully",
                        response
                )
        );
    }
}
