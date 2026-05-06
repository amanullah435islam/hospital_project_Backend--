package com.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.model.Appointment;
import com.hospital.model.Patient;

public interface IAppointmentRepo extends JpaRepository<Appointment, Long>{

}
