package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.model.Patient;
import com.hospital.model.TestPayment;

public interface ITestPaymentRepo extends JpaRepository<TestPayment, Long>{

}
