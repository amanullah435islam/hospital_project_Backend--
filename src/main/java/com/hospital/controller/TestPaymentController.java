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
import com.hospital.model.TestPayment;
import com.hospital.serviceimp.TestPaymentServiceImp;

import lombok.RequiredArgsConstructor;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/TestPayment")

//@RequiredArgsConstructor
public class TestPaymentController {

	@Autowired
	private TestPaymentServiceImp service;

	
	
	   @GetMapping("/{id}")
	   public TestPayment getById(@PathVariable Long id) {
	       return service.getById(id);
	   }
	   
		
		@PostMapping("/save")
		public TestPayment save(@RequestBody TestPayment TestPayment) {
			return service.createTestPayment(TestPayment);
			
		}
		
		@GetMapping("/get")
		public List<TestPayment> get(){
			
			return service.getAllTestPayment();
			
		}
		

	    @PutMapping("/{id}")
	    public TestPayment update(@PathVariable Long id, @RequestBody TestPayment TestPayment){
	        return service.updateTestPayment(id, TestPayment);
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id){
	        service.deleteTestPayment(id);
			System.out.println("aman");
	    }
	    
	    
	    
	    // 🔹 Get By Test Code
	    @GetMapping("/code/{testCode}")
	    public List<TestPayment> getByTestCode(@PathVariable String testCode) {
	        return service.getByTestCode(testCode);
	    }

}
