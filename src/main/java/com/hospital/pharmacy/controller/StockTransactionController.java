package com.hospital.pharmacy.controller;

import com.hospital.common.response.ApiResponse;
import com.hospital.pharmacy.dto.StockTransactionResponse;
import com.hospital.pharmacy.service.StockTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stock-transactions")
@RequiredArgsConstructor
public class StockTransactionController {

    private final StockTransactionService
            stockTransactionService;


    @GetMapping("/batch/{batchId}")
    public ResponseEntity<
            ApiResponse<List<StockTransactionResponse>>
            > getTransactionsByBatch(
            @PathVariable Long batchId
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Stock transactions retrieved successfully",
                        stockTransactionService
                                .getTransactionsByBatch(batchId)
                )
        );
    }
}
