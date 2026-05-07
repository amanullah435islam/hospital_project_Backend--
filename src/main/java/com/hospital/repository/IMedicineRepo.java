package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.model.Medicine;


@Repository
public interface IMedicineRepo extends JpaRepository<Medicine, Long>{
	Medicine getMedicineById(Long id);
}
