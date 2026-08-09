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

import com.hospital.model.Doctor;
import com.hospital.serviceimp.DoctorServiceImp;

import lombok.RequiredArgsConstructor;


//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/doctor")

//@RequiredArgsConstructor
public class DoctorController {

	@Autowired
	private DoctorServiceImp service;
	
	
	@PostMapping("/save")
	public Doctor save(@RequestBody Doctor doctor) {
		return service.createDoctor(doctor);
		
	}
	
	@GetMapping("/getAll")
	public List<Doctor> get(){
		
		return service.getAllDoctor();
		
	}
	
	@GetMapping("/{id}")
	    public Doctor getById(@PathVariable Long id){
	        return service.getDoctorById(id);
	    }

    @PutMapping("/{id}")
    public Doctor update(@PathVariable Long id, @RequestBody Doctor doctor){
        return service.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteDoctor(id);
		System.out.println("aman");
    }
}

