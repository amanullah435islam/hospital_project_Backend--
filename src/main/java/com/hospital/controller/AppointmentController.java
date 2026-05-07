package com.hospital.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.model.Appointment;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.serviceimp.AppointmentServiceImp;
import com.hospital.serviceimp.DoctorServiceImp;
import com.hospital.serviceimp.PatientServiceImp;

import lombok.RequiredArgsConstructor;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointment")

@RequiredArgsConstructor
public class AppointmentController  {

	@Autowired
    private AppointmentServiceImp appointmentService;

	@Autowired
	private PatientServiceImp patientService;

	@Autowired
	private DoctorServiceImp doctorService;
	


	@GetMapping("/appointment/getMeta")
	public Map<String, Object> getAllMeta() {
		List<Appointment> apps = appointmentService.getAllAppointment();
		List<Patient> ptns = patientService.getAllPatient();
		List<Doctor> dcrs = doctorService.getAllDoctor();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Appointment", apps);
		map.put("patient", ptns);
		map.put("doctor", dcrs);


		return map;
	}
	
    @GetMapping("/appointment/{id}")
    public Appointment getById(@PathVariable Long id) {
    	return appointmentService.getAppointmentById(id);
    }
    
	@PostMapping("/save")
	public Appointment save(@RequestBody Appointment appointment) {
		return appointmentService.createAppointment(appointment);
		
	}
	
	@GetMapping("/get")
	public List<Appointment> get(){
		
		return appointmentService.getAllAppointment();
		
	}
	

    @PutMapping("/{id}")
    public Appointment update(@PathVariable Long id, @RequestBody Appointment appointment){
        return appointmentService.updateAppointment(id, appointment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
    	appointmentService.deleteAppointment(id);
		System.out.println("aman");
    }
    
    
    
    
//   approved
//  @GetMapping("/appointment/approve")
//  public List<Appointment> getAllApp() {
//      return appointmentDAO.getAllApp();
//  }
	
  // 🔹 Get active (status = 1)
  @GetMapping("/active")
  public List<Appointment> getActive() {
      return appointmentService.getActive();
  }

  // 🔹 Update status
  @PatchMapping("/{id}/status")
  public Appointment updateStatus(
          @PathVariable Long id,
          @RequestParam int status
  ) {
      return appointmentService.updateStatus(id, status);
  }
}

