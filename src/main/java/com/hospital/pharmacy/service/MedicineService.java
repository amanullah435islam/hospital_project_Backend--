package com.hospital.pharmacy.service;

import com.hospital.pharmacy.dto.MedicineCreateRequest;
import com.hospital.pharmacy.dto.MedicineResponse;
import java.util.List;

public interface MedicineService {

    MedicineResponse createMedicine(
            MedicineCreateRequest request
    );

    MedicineResponse getMedicineById(
            Long id
    );

    List<MedicineResponse> getAllMedicines();

    List<MedicineResponse> getActiveMedicines();

    List<MedicineResponse> searchMedicines(
            String name
    );
}
