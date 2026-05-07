package com.hospital.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.model.Medicine;
import com.hospital.repository.IMedicineRepo;
import com.hospital.service.MedicineService;

@Service
public class MedicineServiceImp implements MedicineService{

	@ Autowired
	private IMedicineRepo medicineRepo;
	
	
	@Override
	public Medicine createMedicine(Medicine p) {
		
		return medicineRepo.save(p);
	}

	@Override
	public List<Medicine> getAllMedicine() {
		
		return medicineRepo.findAll();
	}

	@Override
	public Medicine updateMedicine(Long id, Medicine p) {
		
		Medicine existing = medicineRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Patient not found"));
		
		existing.setAppointmentId(p.getAppointmentId());
		existing.setAppointmentCode(p.getAppointmentCode());
		existing.setDose(p.getDose());
		existing.setDuration(p.getDuration());
		existing.setFrequency(p.getFrequency());
		existing.setMedicineCode(p.getMedicineCode());
		existing.setMedicineName(p.getMedicineName());
		existing.setPrescriptionId(p.getPrescriptionId());
		existing.setPrescriptionCode(p.getPrescriptionCode());
		
		
		return medicineRepo.save(existing);
	}

	@Override
	public void deleteMedicine(Long id) {
		
		medicineRepo.deleteById(id);
		
	}

	@Override
	public Medicine getMedicineById(Long id) {
		// TODO Auto-generated method stub
		return medicineRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
	}

}
