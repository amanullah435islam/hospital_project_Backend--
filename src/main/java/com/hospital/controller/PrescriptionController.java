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
import com.hospital.model.Prescription;
import com.hospital.serviceimp.PrescriptionServiceImp;

import lombok.RequiredArgsConstructor;


	//@CrossOrigin(origins = "*")
	@RestController
	@RequestMapping("/api/prescription") 

//	@RequiredArgsConstructor
	public class PrescriptionController {


		@Autowired
		private PrescriptionServiceImp service;
		
		
	    @GetMapping("/{id}")
	    public Prescription getById(@PathVariable Long id) {
	        return service.getPrescriptionById(id);
	    }

		@PostMapping("/save")
		public Prescription save(@RequestBody Prescription Prescription) {
			return service.createPrescription(Prescription);
			
		}
		
		@GetMapping("/get")
		public List<Prescription> get(){
			
			return service.getAllPrescription();
			
		}
		

	    @PutMapping("/{id}")
	    public Prescription update(@PathVariable Long id, @RequestBody Prescription Prescription){
	        return service.updatePrescription(id, Prescription);
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id){
	        service.deletePrescription(id);
			System.out.println("aman");
	    }
	    
	}
	
	
