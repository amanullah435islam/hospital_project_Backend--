package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.model.Prescription;

@Repository
public interface IPrescriptionRepo extends JpaRepository<Prescription, Long>{
	Prescription getPrescriptionById(Long id);
}
