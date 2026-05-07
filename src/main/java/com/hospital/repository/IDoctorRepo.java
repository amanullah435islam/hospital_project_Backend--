package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.model.Doctor;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor, Long> {
	Doctor getDoctorById(Long id);
}
