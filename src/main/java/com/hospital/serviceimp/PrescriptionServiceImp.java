package com.hospital.serviceimp;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.model.Prescription;
import com.hospital.repository.IPrescriptionRepo;
import com.hospital.service.PrescriptionService;

@Service
//@RequiredArgsConstructor
public class PrescriptionServiceImp implements PrescriptionService{


	@Autowired
	private IPrescriptionRepo prescriptionRepo;
	
	
	@Override
	public Prescription createPrescription(Prescription p) {
		
		return prescriptionRepo.save(p);
	}

	@Override
	public List<Prescription> getAllPrescription() {
		
		return prescriptionRepo.findAll();
	}

	@Override
	public Prescription updatePrescription(Long id, Prescription p) {
		
		Prescription existing = prescriptionRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Prescription not found"));
		
		existing.setAge(p.getAge());
		existing.setAppointmentCode(p.getAppointmentCode());
		existing.setAppointmentId(p.getAppointmentId());
		existing.setAvailability(p.getAvailability());
		existing.setBookingDate(p.getBookingDate());
		existing.setContact(p.getContact());
		existing.setDate(p.getDate());
		existing.setDepartment(p.getDepartment());
		existing.setDob(p.getDob());
		existing.setDoctorCode(p.getDoctorCode());
		existing.setDoctorId(p.getDoctorId());
		existing.setDoctorName(p.getDoctorName());
		existing.setEmail(p.getEmail());
		existing.setGender(p.getGender());
		existing.setLastVisit(p.getLastVisit());
		existing.setMadicleHistry(p.getMadicleHistry());
		existing.setPatientCode(p.getPatientCode());
		existing.setPatientId(p.getPatientId());
		existing.setPatientName(p.getPatientName());
		existing.setPaymentStatus(p.getPaymentStatus());
		existing.setPhone(p.getPhone());
		existing.setPrescriptionCode(p.getPrescriptionCode());
		existing.setRoomNumber(p.getRoomNumber());
		existing.setSpecialize(p.getSpecialize());
		existing.setStatus(p.getStatus());
		
		return prescriptionRepo.save(existing);
	}

	@Override
	public void deletePrescription(Long id) {
	
		prescriptionRepo.deleteById(id);
	}

	@Override
	public Prescription getPrescriptionById(Long id) {
		// TODO Auto-generated method stub
		return prescriptionRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));
	}

}
