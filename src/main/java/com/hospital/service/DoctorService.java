package com.hospital.service;

import java.util.List;
import com.hospital.model.Doctor;

public interface DoctorService {

	
	Doctor createDoctor(Doctor p);
	
	List<Doctor> getAllDoctor();
	
	Doctor updateDoctor(Long id, Doctor p);
	
	void deleteDoctor(Long id);
}
