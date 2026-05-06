package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.model.LabTest;


@Repository
public interface ILabTestRepo extends JpaRepository<LabTest, Long>{

}
