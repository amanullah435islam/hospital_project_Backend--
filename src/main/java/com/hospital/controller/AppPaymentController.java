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
import com.hospital.model.AppPayment;
import com.hospital.serviceimp.AppPaymentServiceImp;

import lombok.RequiredArgsConstructor;

	//@CrossOrigin(origins = "*")
	@RestController
	@RequestMapping("/api/appPayment")
	
	@RequiredArgsConstructor
	public class AppPaymentController {

		
		@Autowired
		private AppPaymentServiceImp service;

		   @GetMapping("/{id}")
		   public AppPayment getById(@PathVariable Long id) {
		       return service.getAppPaymentById(id);
		   }
		   
			
			@PostMapping("/save")
			public AppPayment save(@RequestBody AppPayment AppPayment) {
				return service.createAppPayment(AppPayment);
				
			}
			
			@GetMapping("/get")
			public List<AppPayment> get(){
				
				return service.getAllAppPayment();
				
			}
			

		    @PutMapping("/{id}")
		    public AppPayment update(@PathVariable Long id, @RequestBody AppPayment AppPayment){
		        return service.updateAppPayment(id, AppPayment);
		    }

		    @DeleteMapping("/{id}")
		    public void delete(@PathVariable Long id){
		        service.deleteAppPayment(id);
				System.out.println("aman");
		    }

	}

