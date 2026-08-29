package com.hospital.pharmacy.service;

import com.hospital.pharmacy.dto.StockTransactionResponse;
import java.util.List;

public interface StockTransactionService {

    List<StockTransactionResponse> getTransactionsByBatch(
            Long batchId
    );
}
