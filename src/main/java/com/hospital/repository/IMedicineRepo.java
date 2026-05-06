package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.model.Medicine;
import com.hospital.model.Patient;

public interface IMedicineRepo extends JpaRepository<Medicine, Long>{

}
