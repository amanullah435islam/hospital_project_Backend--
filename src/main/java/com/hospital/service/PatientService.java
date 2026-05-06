package com.hospital.service;

import java.util.List;
import com.hospital.model.Patient;

public interface PatientService {

	
	Patient createPatient(Patient p);
	
	List<Patient> getAllPatient();
	
	Patient updatePatient(Long id, Patient p);
	
	void deletePatient(Long id);
}
