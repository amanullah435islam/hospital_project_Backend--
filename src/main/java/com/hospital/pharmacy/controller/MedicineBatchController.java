package com.hospital.pharmacy.controller;

import com.hospital.common.response.ApiResponse;
import com.hospital.pharmacy.dto.MedicineBatchCreateRequest;
import com.hospital.pharmacy.dto.MedicineBatchResponse;
import com.hospital.pharmacy.dto.StockTransactionResponse;
import com.hospital.pharmacy.service.MedicineBatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/medicine-batches")
@RequiredArgsConstructor
public class MedicineBatchController {

    private final MedicineBatchService
            medicineBatchService;


    @PostMapping
    public ResponseEntity<
            ApiResponse<MedicineBatchResponse>
            > createBatch(

            @Valid
            @RequestBody
            MedicineBatchCreateRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Medicine batch created successfully",
                                medicineBatchService
                                        .createBatch(request)
                        )
                );
    }


    @GetMapping("/{id}")
    public ResponseEntity<
            ApiResponse<MedicineBatchResponse>
            > getBatchById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Medicine batch retrieved successfully",
                        medicineBatchService
                                .getBatchById(id)
                )
        );
    }


    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<
            ApiResponse<List<MedicineBatchResponse>>
            > getBatchesByMedicine(
            @PathVariable Long medicineId
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Medicine batches retrieved successfully",
                        medicineBatchService
                                .getBatchesByMedicine(
                                        medicineId
                                )
                )
        );
    }


    @GetMapping("/available")
    public ResponseEntity<
            ApiResponse<List<MedicineBatchResponse>>
            > getAvailableBatches() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Available medicine batches retrieved successfully",
                        medicineBatchService
                                .getAvailableBatches()
                )
        );
    }

}