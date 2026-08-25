package com.hospital.medical.service;

import com.hospital.medical.dto.MedicalRecordCreateRequest;
import com.hospital.medical.dto.MedicalRecordResponse;
import java.util.List;

public interface MedicalRecordService {

    MedicalRecordResponse createMedicalRecord(
            MedicalRecordCreateRequest request
    );

    MedicalRecordResponse getMedicalRecordById(
            Long id
    );

    MedicalRecordResponse getByAppointmentId(
            Long appointmentId
    );

    List<MedicalRecordResponse> getPatientMedicalHistory(
            Long patientId
    );

    List<MedicalRecordResponse> getDoctorMedicalRecords(
            Long doctorId
    );
}