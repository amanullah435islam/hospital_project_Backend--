package com.hospital.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.model.Doctor;
import com.hospital.repository.IDoctorRepo;
import com.hospital.service.DoctorService;

@Service
public class DoctorServiceImp implements DoctorService{

	
	@Autowired
	private IDoctorRepo doctorRepo;
	
	
	@Override
	public Doctor createDoctor(Doctor p) {
		
		return doctorRepo.save(p);
	}

	@Override
	public List<Doctor> getAllDoctor() {
		
		return doctorRepo.findAll();
	}

	@Override
	public Doctor updateDoctor(Long id, Doctor p) {

		Doctor existing = doctorRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Doctor not found"));
		
		existing.setDoctorCode(p.getDoctorCode());
		existing.setDoctorName(p.getDoctorName());
		existing.setEmail(p.getEmail());
		existing.setAvailability(p.getAvailability());
		existing.setContact(p.getContact());
		existing.setDescription(p.getDescription());
		existing.setImage(p.getImage());
		existing.setRoomNumber(p.getRoomNumber());
		existing.setSpecialize(p.getSpecialize());		
		
		return doctorRepo.save(existing);
	}

	@Override
	public void deleteDoctor(Long id) {
		
		doctorRepo.deleteById(id);
		
	}

	@Override
	public Doctor getDoctorById(Long id) {
		// TODO Auto-generated method stub
		return doctorRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
	}

}
