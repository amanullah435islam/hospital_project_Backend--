package com.hospital.medical.controller;


import com.hospital.common.response.ApiResponse;
import com.hospital.medical.dto.MedicalRecordCreateRequest;
import com.hospital.medical.dto.MedicalRecordResponse;
import com.hospital.medical.service.MedicalRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;


    @PostMapping
    public ResponseEntity<
            ApiResponse<MedicalRecordResponse>
            > createMedicalRecord(

            @Valid
            @RequestBody
            MedicalRecordCreateRequest request
    ) {

        MedicalRecordResponse response =
                medicalRecordService
                        .createMedicalRecord(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Medical record created successfully",
                                response
                        )
                );
    }


    @GetMapping("/{id}")
    public ResponseEntity<
            ApiResponse<MedicalRecordResponse>
            > getMedicalRecord(
            @PathVariable Long id
    ) {

        MedicalRecordResponse response =
                medicalRecordService
                        .getMedicalRecordById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Medical record retrieved successfully",
                        response
                )
        );
    }


    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<
            ApiResponse<MedicalRecordResponse>
            > getByAppointment(
            @PathVariable Long appointmentId
    ) {

        MedicalRecordResponse response =
                medicalRecordService
                        .getByAppointmentId(
                                appointmentId
                        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Medical record retrieved successfully",
                        response
                )
        );
    }


    @GetMapping("/patient/{patientId}")
    public ResponseEntity<
            ApiResponse<List<MedicalRecordResponse>>
            > getPatientHistory(
            @PathVariable Long patientId
    ) {

        List<MedicalRecordResponse> response =
                medicalRecordService
                        .getPatientMedicalHistory(
                                patientId
                        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Patient medical history retrieved successfully",
                        response
                )
        );
    }


    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<
            ApiResponse<List<MedicalRecordResponse>>
            > getDoctorRecords(
            @PathVariable Long doctorId
    ) {

        List<MedicalRecordResponse> response =
                medicalRecordService
                        .getDoctorMedicalRecords(
                                doctorId
                        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Doctor medical records retrieved successfully",
                        response
                )
        );
    }
}
