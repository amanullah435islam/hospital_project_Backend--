package com.hospital.patient.controller;

import com.hospital.common.response.ApiResponse;
import com.hospital.patient.dto.PatientCreateRequest;
import com.hospital.patient.dto.PatientResponse;
import com.hospital.patient.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;


    @PostMapping
    public ResponseEntity<ApiResponse<PatientResponse>>
    createPatient(
            @Valid @RequestBody
            PatientCreateRequest request
    ) {

        PatientResponse response =
                patientService.createPatient(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Patient created successfully",
                                response
                        )
                );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PatientResponse>>
    getPatient(
            @PathVariable Long id
    ) {

        PatientResponse response =
                patientService.getPatientById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Patient retrieved successfully",
                        response
                )
        );
    }


    @GetMapping("/code/{patientCode}")
    public ResponseEntity<ApiResponse<PatientResponse>>
    getPatientByCode(
            @PathVariable String patientCode
    ) {

        PatientResponse response =
                patientService.getPatientByCode(
                        patientCode
                );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Patient retrieved successfully",
                        response
                )
        );
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<PatientResponse>>>
    getAllPatients() {

        List<PatientResponse> response =
                patientService.getAllPatients();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Patients retrieved successfully",
                        response
                )
        );
    }
}
