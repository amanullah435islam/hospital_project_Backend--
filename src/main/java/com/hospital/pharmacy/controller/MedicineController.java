package com.hospital.pharmacy.controller;

import com.hospital.common.response.ApiResponse;
import com.hospital.pharmacy.dto.MedicineCreateRequest;
import com.hospital.pharmacy.dto.MedicineResponse;
import com.hospital.pharmacy.service.MedicineService;
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


    @PostMapping
    public ResponseEntity<
            ApiResponse<MedicineResponse>
            > createMedicine(

            @Valid
            @RequestBody
            MedicineCreateRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Medicine created successfully",
                                medicineService
                                        .createMedicine(request)
                        )
                );
    }


    @GetMapping
    public ResponseEntity<
            ApiResponse<List<MedicineResponse>>
            > getAllMedicines() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Medicines retrieved successfully",
                        medicineService
                                .getAllMedicines()
                )
        );
    }


    @GetMapping("/active")
    public ResponseEntity<
            ApiResponse<List<MedicineResponse>>
            > getActiveMedicines() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Active medicines retrieved successfully",
                        medicineService
                                .getActiveMedicines()
                )
        );
    }


    @GetMapping("/search")
    public ResponseEntity<
            ApiResponse<List<MedicineResponse>>
            > searchMedicines(

            @RequestParam
            String name
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Medicine search completed successfully",
                        medicineService
                                .searchMedicines(name)
                )
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<
            ApiResponse<MedicineResponse>
            > getMedicineById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Medicine retrieved successfully",
                        medicineService
                                .getMedicineById(id)
                )
        );
    }
}
