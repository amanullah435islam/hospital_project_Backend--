package com.hospital.service.controller;

import com.hospital.common.response.ApiResponse;
import com.hospital.service.dto.ServiceCreateRequest;
import com.hospital.service.dto.ServiceResponse;
import com.hospital.service.dto.ServiceUpdateRequest;
import com.hospital.service.entity.ServiceStatus;
import com.hospital.service.service.HospitalServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class HospitalServiceController {

    private final HospitalServiceService serviceService;


    // ==========================================
    // CREATE
    // ==========================================

    @PostMapping
    public ResponseEntity<
            ApiResponse<ServiceResponse>
            > createService(

            @Valid
            @RequestBody
            ServiceCreateRequest request
    ) {

        ServiceResponse response =
                serviceService.createService(
                        request
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Hospital service created successfully",
                                response
                        )
                );
    }


    // ==========================================
    // GET ALL
    // ==========================================

    @GetMapping
    public ResponseEntity<
            ApiResponse<List<ServiceResponse>>
            > getAllServices() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Hospital services retrieved successfully",
                        serviceService.getAllServices()
                )
        );
    }


    // ==========================================
    // GET ACTIVE
    // ==========================================

    @GetMapping("/active")
    public ResponseEntity<
            ApiResponse<List<ServiceResponse>>
            > getActiveServices() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Active services retrieved successfully",
                        serviceService.getActiveServices()
                )
        );
    }


    // ==========================================
    // SEARCH
    // ==========================================

    @GetMapping("/search")
    public ResponseEntity<
            ApiResponse<List<ServiceResponse>>
            > searchServices(

            @RequestParam
            String name
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Service search completed successfully",
                        serviceService.searchServices(name)
                )
        );
    }


    // ==========================================
    // GET BY ID
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<
            ApiResponse<ServiceResponse>
            > getServiceById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Hospital service retrieved successfully",
                        serviceService.getServiceById(id)
                )
        );
    }


    // ==========================================
    // UPDATE
    // ==========================================

    @PutMapping("/{id}")
    public ResponseEntity<
            ApiResponse<ServiceResponse>
            > updateService(

            @PathVariable Long id,

            @Valid
            @RequestBody
            ServiceUpdateRequest request
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Hospital service updated successfully",
                        serviceService.updateService(
                                id,
                                request
                        )
                )
        );
    }


    // ==========================================
    // STATUS
    // ==========================================

    @PatchMapping("/{id}/status")
    public ResponseEntity<
            ApiResponse<ServiceResponse>
            > updateServiceStatus(

            @PathVariable Long id,

            @RequestParam
            ServiceStatus status
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Service status updated successfully",
                        serviceService.updateServiceStatus(
                                id,
                                status
                        )
                )
        );
    }
}
