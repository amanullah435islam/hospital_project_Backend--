package com.hospital.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.model.Appointment;
import com.hospital.repository.IAppointmentRepo;
import com.hospital.service.AppointmentService;

@Service
public class AppointmentServiceImp implements AppointmentService{

	@Autowired
	private IAppointmentRepo appointmentRepo;
	
	@Override
	public Appointment createAppointment(Appointment p) {
		
		return appointmentRepo.save(p);
	}

	@Override
	public List<Appointment> getAllAppointment() {
		
		return appointmentRepo.findAll();
	}

	@Override
	public Appointment updateAppointment(Long id, Appointment p) {

		Appointment existing = appointmentRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Appointment not found"));

		existing.setAppointmentCode(p.getAppointmentCode());
		existing.setBookingDate(p.getBookingDate());
		existing.setDate(p.getDate());
		existing.setDepartment(p.getDepartment());
		existing.setDoctorCode(p.getDoctorCode());
		existing.setDoctorId(p.getDoctorId());
		existing.setDoctorName(p.getDoctorName());
		existing.setMadicleHistry(p.getMadicleHistry());
		existing.setPatientCode(p.getPatientCode());
		existing.setPatientId(p.getPatientId());
		existing.setPatientName(p.getPatientName());
		existing.setPaymentStatus(p.getPaymentStatus());
		existing.setStatus(p.getStatus());
		
		return appointmentRepo.save(existing);
	}

	@Override
	public void deleteAppointment(Long id) {
		
		appointmentRepo.deleteById(id);
		
	}

	@Override
	public Appointment getAppointmentById(Long id) {

	    return appointmentRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
	}

	
	
	
	
	 @Override
	    public List<Appointment> getActive() {
	        return appointmentRepo.findByStatus(1);
	    }

	 
	    @Override
	    public Appointment updateStatus(Long id, int status) {

	        Appointment app = appointmentRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Appointment not found"));

	        app.setStatus(status);

	        return appointmentRepo.save(app);
	    }



}
