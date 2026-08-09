package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hospital.model.Patient;
import com.hospital.serviceimp.PatientServiceImp;

import lombok.RequiredArgsConstructor;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/patient")

//@RequiredArgsConstructor
public class PatientController {

	@Autowired
	private PatientServiceImp service;
	
	
	@PostMapping("/save")
	public Patient save(@RequestBody Patient patient) {
		return service.createPatient(patient);
		
	}

	@GetMapping("/getAll")
	public List<Patient> get(){
		
		return service.getAllPatient();
		
	}
	
	@GetMapping("/{id}")
	public Patient getById(@PathVariable Long id) {
	    return service.getPatientById(id);
	}

    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient patient){
        return service.updatePatient(id, patient);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deletePatient(id);
		System.out.println("aman");
    }
}
