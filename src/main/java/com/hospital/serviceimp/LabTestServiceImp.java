package com.hospital.serviceimp;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.model.LabTest;
import com.hospital.repository.ILabTestRepo;
import com.hospital.service.LabTestService;

@Service
//@RequiredArgsConstructor
public class LabTestServiceImp implements LabTestService{

	@Autowired
	private ILabTestRepo labTestRepo;
	
	@Override
	public LabTest createLabTest(LabTest p) {
		
		return labTestRepo.save(p);
	}

	@Override
	public List<LabTest> getAllLabTest() {
		
		return labTestRepo.findAll();
	}

	@Override
	public LabTest updateLabTest(Long id, LabTest p) {
		LabTest existing = labTestRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("LabTest not found"));
		
		existing.setAppointmentId(p.getAppointmentId());	
		existing.setAppointmentCode(p.getAppointmentCode());
		existing.setDescription(p.getDescription());
		existing.setNotes(p.getNotes());
		existing.setPrescriptionCode(p.getPrescriptionCode());
		existing.setPrescriptionId(p.getPrescriptionId());
		existing.setReportedBy(p.getReportedBy());
		existing.setTestCode(p.getTestCode());
		existing.setTestName(p.getTestName());
		existing.setTestType(p.getTestType());
				
		return labTestRepo.save(existing);
	}

	@Override
	public void deleteLabTest(Long id) {

		labTestRepo.deleteById(id);
		
	}

	@Override
	public LabTest getLabTestById(Long id) {
		// TODO Auto-generated method stub
		return labTestRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("LabTest not found with id: " + id));
	}

}
