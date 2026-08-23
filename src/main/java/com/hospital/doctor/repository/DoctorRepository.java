package com.hospital.doctor.repository;

import com.hospital.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository
        extends JpaRepository<Doctor, Long> {

    boolean existsByDoctorCode(String doctorCode);

    boolean existsByUserId(Long userId);

    boolean existsByLicenseNumber(String licenseNumber);

    Optional<Doctor> findByUserId(Long userId);
}
