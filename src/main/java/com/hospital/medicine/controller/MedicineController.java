package com.hospital.medicine.controller;

import com.hospital.common.response.ApiResponse;
import com.hospital.medicine.dto.MedicineCreateRequest;
import com.hospital.medicine.dto.MedicineResponse;
import com.hospital.medicine.dto.MedicineUpdateRequest;
import com.hospital.medicine.entity.MedicineStatus;
import com.hospital.medicine.service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;


    // ==========================================
    // CREATE
    // ==========================================

    @PostMapping
    public ResponseEntity<
            ApiResponse<MedicineResponse>
            > createMedicine(

            @Valid
            @RequestBody
            MedicineCreateRequest request
    ) {

        MedicineResponse response =
                medicineService.createMedicine(
                        request
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Medicine created successfully",
                                response
                        )
                );
    }


    // ==========================================
    // GET ALL
    // ==========================================

    @GetMapping
    public ResponseEntity<
            ApiResponse<List<MedicineResponse>>
            > getAllMedicines() {

        List<MedicineResponse> medicines =
                medicineService.getAllMedicines();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Medicines retrieved successfully",
                        medicines
                )
        );
    }


// ==========================================
// GET ACTIVE MEDICINES
// ==========================================

    @GetMapping("/active")
    public ResponseEntity<
            ApiResponse<List<MedicineResponse>>
            > getActiveMedicines() {

        List<MedicineResponse> medicines =
                medicineService.getActiveMedicines();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Active medicines retrieved successfully",
                        medicines
                )
        );
    }

    // ==========================================
    // SEARCH
    // ==========================================

    @GetMapping("/search")
    public ResponseEntity<
            ApiResponse<List<MedicineResponse>>
            > searchMedicines(

            @RequestParam
            String name
    ) {

        List<MedicineResponse> medicines =
                medicineService.searchMedicines(
                        name
                );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Medicine search completed successfully",
                        medicines
                )
        );
    }


    // ==========================================
    // GET BY ID
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<
            ApiResponse<MedicineResponse>
            > getMedicineById(

            @PathVariable
            Long id
    ) {

        MedicineResponse response =
                medicineService.getMedicineById(
                        id
                );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Medicine retrieved successfully",
                        response
                )
        );
    }


    // ==========================================
    // UPDATE
    // ==========================================

    @PutMapping("/{id}")
    public ResponseEntity<
            ApiResponse<MedicineResponse>
            > updateMedicine(

            @PathVariable
            Long id,

            @Valid
            @RequestBody
            MedicineUpdateRequest request
    ) {

        MedicineResponse response =
                medicineService.updateMedicine(
                        id,
                        request
                );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Medicine updated successfully",
                        response
                )
        );
    }


    // ==========================================
    // STATUS
    // ==========================================

    @PatchMapping("/{id}/status")
    public ResponseEntity<
            ApiResponse<MedicineResponse>
            > updateMedicineStatus(

            @PathVariable
            Long id,

            @RequestParam
            MedicineStatus status
    ) {

        MedicineResponse response =
                medicineService.updateMedicineStatus(
                        id,
                        status
                );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Medicine status updated successfully",
                        response
                )
        );
    }
}
