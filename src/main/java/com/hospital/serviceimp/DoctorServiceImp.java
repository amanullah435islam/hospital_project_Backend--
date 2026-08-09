package com.hospital.serviceimp;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.model.Doctor;
import com.hospital.repository.IDoctorRepo;
import com.hospital.service.DoctorService;

@Service
//@RequiredArgsConstructor
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
		existing.setSpecialize(p.getSpecialize());
		existing.setContact(p.getContact());	
		existing.setAvailability(p.getAvailability());
		existing.setEmail(p.getEmail());
		existing.setRoomNumber(p.getRoomNumber());
		existing.setDescription(p.getDescription());
		existing.setImage(p.getImage());
		
				
		
		return doctorRepo.save(existing);
	}

	@Override
	public void deleteDoctor(Long id) {
		
		doctorRepo.deleteById(id);
		
	}

	@Override
	public Doctor getDoctorById(Long id) {
		
		return doctorRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
	}

}
