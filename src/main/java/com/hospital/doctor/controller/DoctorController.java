package com.hospital.doctor.controller;

import com.hospital.common.response.ApiResponse;
import com.hospital.doctor.dto.DoctorCreateRequest;
import com.hospital.doctor.dto.DoctorResponse;
import com.hospital.doctor.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<ApiResponse<DoctorResponse>>
    createDoctor(
            @Valid @RequestBody
            DoctorCreateRequest request
    ) {

        DoctorResponse response =
                doctorService.createDoctor(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Doctor created successfully",
                                response
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorResponse>>
    getDoctor(
            @PathVariable Long id
    ) {

        DoctorResponse response =
                doctorService.getDoctorById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Doctor retrieved successfully",
                        response
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DoctorResponse>>>
    getAllDoctors() {

        List<DoctorResponse> response =
                doctorService.getAllDoctors();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Doctors retrieved successfully",
                        response
                )
        );
    }
}
