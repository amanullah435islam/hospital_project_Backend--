package com.hospital.pharmacy.service;

import com.hospital.pharmacy.dto.MedicineBatchCreateRequest;
import com.hospital.pharmacy.dto.MedicineBatchResponse;
import java.util.List;

public interface MedicineBatchService {

    MedicineBatchResponse createBatch(
            MedicineBatchCreateRequest request
    );

    MedicineBatchResponse getBatchById(
            Long id
    );

    List<MedicineBatchResponse> getBatchesByMedicine(
            Long medicineId
    );

    List<MedicineBatchResponse> getAvailableBatches();
}