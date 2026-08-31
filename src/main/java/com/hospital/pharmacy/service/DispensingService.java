package com.hospital.pharmacy.service;

import com.hospital.pharmacy.dto.DispensePrescriptionRequest;
import com.hospital.pharmacy.dto.DispensePrescriptionResponse;

public interface DispensingService {

   DispensePrescriptionResponse dispensePrescription(
            DispensePrescriptionRequest request
    );
}
