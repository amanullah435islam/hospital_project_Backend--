package com.hospital.patient.service;

import com.hospital.patient.dto.PatientCreateRequest;
import com.hospital.patient.dto.PatientResponse;
import java.util.List;

public interface PatientService {

    PatientResponse createPatient(
            PatientCreateRequest request
    );

    PatientResponse getPatientById(Long id);

    PatientResponse getPatientByCode(
            String patientCode
    );

    List<PatientResponse> getAllPatients();
}
