package com.hospital.prescription.service;

import com.hospital.prescription.dto.PrescriptionCreateRequest;
import com.hospital.prescription.dto.PrescriptionResponse;

public interface PrescriptionService {

    PrescriptionResponse createPrescription(
            PrescriptionCreateRequest request
    );

    PrescriptionResponse getPrescriptionById(
            Long id
    );

    PrescriptionResponse getByMedicalRecordId(
            Long medicalRecordId
    );
}
