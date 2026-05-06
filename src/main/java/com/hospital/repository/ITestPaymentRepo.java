package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.model.TestPayment;

@Repository
public interface ITestPaymentRepo extends JpaRepository<TestPayment, Long>{

}
