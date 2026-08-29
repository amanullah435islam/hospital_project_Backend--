package com.hospital.pharmacy.service;

import com.hospital.pharmacy.dto.StockIssueRequest;
import com.hospital.pharmacy.dto.StockIssueResponse;

public interface StockService {

    StockIssueResponse issueStock(
            StockIssueRequest request
    );
}