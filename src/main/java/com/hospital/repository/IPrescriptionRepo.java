package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.model.Patient;
import com.hospital.model.Prescription;

public interface IPrescriptionRepo extends JpaRepository<Prescription, Long>{

}
