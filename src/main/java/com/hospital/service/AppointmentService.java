package com.hospital.service;

import java.util.List;
import com.hospital.model.Appointment;

public interface AppointmentService {

	
	Appointment createAppointment(Appointment p);
	
	List<Appointment> getAllAppointment();
	
	Appointment updateAppointment(Long id, Appointment p);
	
	void deleteAppointment(Long id);
}
