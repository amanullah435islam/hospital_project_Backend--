package com.hospital.patient.repository;

import com.hospital.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository
        extends JpaRepository<Patient, Long> {

    boolean existsByPatientCode(String patientCode);

    boolean existsByPhone(String phone);

    Optional<Patient> findByPatientCode(String patientCode);

    Optional<Patient> findByUserId(Long userId);
}
