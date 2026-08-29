package com.hospital.pharmacy.controller;

import com.hospital.common.response.ApiResponse;
import com.hospital.pharmacy.dto.StockIssueRequest;
import com.hospital.pharmacy.dto.StockIssueResponse;
import com.hospital.pharmacy.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;


    @PostMapping("/issue")
    public ResponseEntity<
            ApiResponse<StockIssueResponse>
            > issueStock(

            @Valid
            @RequestBody
            StockIssueRequest request
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Stock issued successfully",
                        stockService.issueStock(request)
                )
        );
    }
}