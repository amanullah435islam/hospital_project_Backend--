package com.hospital.medicine.service;


import com.hospital.medicine.dto.MedicineCreateRequest;
import com.hospital.medicine.dto.MedicineResponse;
import com.hospital.medicine.dto.MedicineUpdateRequest;
import com.hospital.medicine.entity.MedicineStatus;
import java.util.List;

public interface MedicineService {

    MedicineResponse createMedicine(
            MedicineCreateRequest request
    );

    MedicineResponse getMedicineById(
            Long id
    );

    List<MedicineResponse> getAllMedicines();

    List<MedicineResponse> searchMedicines(
            String name
    );

    List<MedicineResponse> getActiveMedicines();
    
    MedicineResponse updateMedicine(
            Long id,
            MedicineUpdateRequest request
    );

    MedicineResponse updateMedicineStatus(
            Long id,
            MedicineStatus status
    );
}
