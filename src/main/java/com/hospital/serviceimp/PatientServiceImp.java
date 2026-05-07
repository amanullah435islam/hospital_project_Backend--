package com.hospital.serviceimp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.model.Patient;
import com.hospital.repository.IPatientRepo;
import com.hospital.service.PatientService;

@Service
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

	    existing.setPatientName(newData.getPatientName());
	    existing.setAge(newData.getAge());
	    existing.setGender(newData.getGender());
	    existing.setPhone(newData.getPhone());
	    existing.setLastVisit(newData.getLastVisit());
	    existing.setDob(newData.getDob());
	    existing.setPatientCode(newData.getPatientCode());
	    existing.setPatientName(newData.getPatientName());
	    existing.setVisitAmount(newData.getVisitAmount());

	    return iPatientRepo.save(existing);
	}
	
	@Override
	public void deletePatient(Long id) {
		
		iPatientRepo.deleteById(id);
				
	}

	@Override
	public Patient getPatientById(Long id) {
		// TODO Auto-generated method stub
		return iPatientRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
	}

}
