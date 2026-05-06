package com.hospital.service;

import java.util.List;
import com.hospital.model.Prescription;


public interface PrescriptionService {
	
	
	Prescription createPrescription(Prescription p);
	
	List<Prescription> getAllPrescription();
	
	Prescription updatePrescription(Long id, Prescription p);
	
	void deletePrescription(Long id);
}
