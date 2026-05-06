package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hospital.model.AppPayment;


public interface IAppPaymentRepo extends JpaRepository<AppPayment, Long>{

}
