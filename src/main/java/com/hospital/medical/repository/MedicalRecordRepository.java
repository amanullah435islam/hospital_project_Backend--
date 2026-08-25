package com.hospital.medical.repository;

import com.hospital.medical.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MedicalRecordRepository
        extends JpaRepository<MedicalRecord, Long> {

    boolean existsByRecordCode(String recordCode);

    boolean existsByAppointmentId(Long appointmentId);

    Optional<MedicalRecord> findByAppointmentId(
            Long appointmentId
    );

    List<MedicalRecord> findByPatientIdOrderByVisitDateDesc(
            Long patientId
    );

    List<MedicalRecord> findByDoctorIdOrderByVisitDateDesc(
            Long doctorId
    );
}
