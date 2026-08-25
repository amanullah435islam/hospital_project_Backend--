package com.hospital.prescription.repository;

import com.hospital.prescription.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository
        extends JpaRepository<Prescription, Long> {

    boolean existsByPrescriptionCode(
            String prescriptionCode
    );

    boolean existsByMedicalRecordId(
            Long medicalRecordId
    );

    Optional<Prescription> findByMedicalRecordId(
            Long medicalRecordId
    );

    List<Prescription>
    findByPatientIdOrderByPrescribedDateDesc(
            Long patientId
    );

    List<Prescription>
    findByDoctorIdOrderByPrescribedDateDesc(
            Long doctorId
    );
}
