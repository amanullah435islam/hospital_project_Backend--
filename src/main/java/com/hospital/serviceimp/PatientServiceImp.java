package com.hospital.serviceimp;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.model.Patient;
import com.hospital.repository.IPatientRepo;
import com.hospital.service.PatientService;

@Service
//@RequiredArgsConstructor
public class PatientServiceImp implements PatientService{

	@Autowired
	private IPatientRepo iPatientRepo;
	
	@Override
	public Patient createPatient(Patient p) {
		
		return iPatientRepo.save(p);
	}

	@Override
	public List<Patient> getAllPatient() {
		
		return iPatientRepo.findAll();
	}

	@Override
	public Patient updatePatient(Long id, Patient newData) {

	    Patient existing = iPatientRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Patient not found"));

	    existing.setPatientCode(newData.getPatientCode());
	    existing.setPatientName(newData.getPatientName());
	    existing.setVisitAmount(newData.getVisitAmount());
	    existing.setAge(newData.getAge());
	    existing.setDob(newData.getDob());
	    existing.setGender(newData.getGender());
	    existing.setPhone(newData.getPhone());
	    existing.setLastVisit(newData.getLastVisit());	   	    

	    return iPatientRepo.save(existing);
	}
	
	@Override
	public void deletePatient(Long id) {
		
		iPatientRepo.deleteById(id);
				
	}

	@Override
	public Patient getPatientById(Long id) {
		
		return iPatientRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
	}

}
