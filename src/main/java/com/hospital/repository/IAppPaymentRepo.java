package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.model.AppPayment;

@Repository
public interface IAppPaymentRepo extends JpaRepository<AppPayment, Long>{

}
