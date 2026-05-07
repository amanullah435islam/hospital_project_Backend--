package com.hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hospital.model.Appointment;


@Repository
public interface IAppointmentRepo extends JpaRepository<Appointment, Long>{
	 Appointment getAppointmentById(Long id);
	 
	  // 🔹 custom query (status = 1)
	    List<Appointment> findByStatus(int status);
}
