package com.hospital.pharmacy.controller;

import com.hospital.common.response.ApiResponse;
import com.hospital.pharmacy.dto.DispensePrescriptionRequest;
import com.hospital.pharmacy.dto.DispensePrescriptionResponse;
import com.hospital.pharmacy.service.DispensingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pharmacy/dispensing")
@RequiredArgsConstructor
public class DispensingController {

    private final DispensingService dispensingService;


    @PostMapping("/prescription")
    public ResponseEntity<
            ApiResponse<DispensePrescriptionResponse>
            > dispensePrescription(

            @Valid
            @RequestBody
            DispensePrescriptionRequest request
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Prescription dispensed successfully",
                        dispensingService
                                .dispensePrescription(
                                        request
                                )
                )
        );
    }
}
