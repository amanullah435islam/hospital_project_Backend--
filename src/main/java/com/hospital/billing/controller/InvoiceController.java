package com.hospital.billing.controller;

import com.hospital.billing.dto.InvoiceCreateRequest;
import com.hospital.billing.dto.InvoiceResponse;
import com.hospital.billing.service.InvoiceService;
import com.hospital.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;


    @PostMapping
    public ResponseEntity<
            ApiResponse<InvoiceResponse>
            > createInvoice(

            @Valid
            @RequestBody
            InvoiceCreateRequest request
    ) {

        InvoiceResponse response =
                invoiceService.createInvoice(
                        request
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Invoice created successfully",
                                response
                        )
                );
    }


    @GetMapping("/{id}")
    public ResponseEntity<
            ApiResponse<InvoiceResponse>
            > getInvoice(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Invoice retrieved successfully",
                        invoiceService.getInvoiceById(id)
                )
        );
    }


    @GetMapping("/patient/{patientId}")
    public ResponseEntity<
            ApiResponse<List<InvoiceResponse>>
            > getPatientInvoices(
            @PathVariable Long patientId
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Patient invoices retrieved successfully",
                        invoiceService
                                .getPatientInvoices(
                                        patientId
                                )
                )
        );
    }


    @PatchMapping("/{id}/cancel")
    public ResponseEntity<
            ApiResponse<InvoiceResponse>
            > cancelInvoice(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Invoice cancelled successfully",
                        invoiceService
                                .cancelInvoice(id)
                )
        );
    }
}
