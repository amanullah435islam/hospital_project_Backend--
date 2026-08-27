package com.hospital.billing.controller;

import com.hospital.billing.dto.PaymentCreateRequest;
import com.hospital.billing.dto.PaymentResponse;
import com.hospital.billing.service.PaymentService;
import com.hospital.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping
    public ResponseEntity<
            ApiResponse<PaymentResponse>
            > createPayment(

            @Valid
            @RequestBody
            PaymentCreateRequest request
    ) {

        PaymentResponse response =
                paymentService.createPayment(
                        request
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Payment recorded successfully",
                                response
                        )
                );
    }


    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<
            ApiResponse<List<PaymentResponse>>
            > getInvoicePayments(
            @PathVariable Long invoiceId
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Invoice payments retrieved successfully",
                        paymentService
                                .getInvoicePayments(
                                        invoiceId
                                )
                )
        );
    }
}
