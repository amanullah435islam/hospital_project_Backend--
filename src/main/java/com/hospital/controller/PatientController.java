package com.hospital.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.dao.PatientDAO;
import com.hospital.model.Patient;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PatientController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired 
	private PatientDAO patientDAO;
	
	
	@GetMapping("/patient")
	public List<Patient> getAllPatients(){
		return patientDAO.getAll();
	}
	
	@PostMapping("/patient")
	public Patient save(@RequestBody Patient p) {
		return patientDAO.save(p);
	}
	
	
	//	//optional cara method:
	    @GetMapping("/patient/{id}")
	    public ResponseEntity<Patient> getById(@PathVariable(value = "id") long id) {
	    	Patient p = patientDAO.getByID(id);
	        return ResponseEntity.ok().body(p);
	    }
	    
	
	    @DeleteMapping("/patient/{id}")
	    public Map<String, Boolean> delete(@PathVariable(value = "id") long patientId){
	    	Patient patient = patientDAO.getByID(patientId);
	    	patientDAO.delete(patient);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> update(@PathVariable(value = "id") long patientId,
         @Validated @RequestBody Patient patientDetails) {

        Patient patient = patientDAO.getByID(patientId);

        patient.setPatientCode(patientDetails.getPatientCode());
        patient.setPatientName(patientDetails.getPatientName());
        patient.setVisitAmount(patientDetails.getVisitAmount());
        patient.setAge(patientDetails.getAge());
        patient.setDob(patientDetails.getDob());
        patient.setGender(patientDetails.getGender());
        patient.setPhone(patientDetails.getPhone());
        patient.setLastVisit(patientDetails.getLastVisit());

        final Patient updatedPatient = patientDAO.update(patient); // ✅ এখানে update() মেথড ব্যবহার করো
        return ResponseEntity.ok(updatedPatient);
    }
    
    
	//	//DAO -te optional use korci tai controller a optional type ar generic use korci
	//	@GetMapping("/patient/{id}")
	//	public ResponseEntity<Optional<Patient>> getByID(@PathVariable (value= "id") long id){
	//		Optional<Patient> p = patientDAO.getByID(id);
	//		return ResponseEntity.ok().body(p);
	//	}
    
    
    
	//	//ai method a getByID() method takar karone dao ar delete method a optional use kora hoice...!
	//    @DeleteMapping("/patient/{id}")
	//    public Map<String, Boolean> delete(@PathVariable(value = "id") long patientId){
	//    	Optional<Patient> patient = patientDAO.getByID(patientId);
	//    	patientDAO.delete(patient);
	//        Map<String, Boolean> response = new HashMap<>();
	//        response.put("deleted", Boolean.TRUE);
	//        return response;
	//    }
}
