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
import com.hospital.model.LabTest;
import com.hospital.serviceimp.LabTestServiceImp;

import lombok.RequiredArgsConstructor;


//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/labtest")


//@RequiredArgsConstructor
public class LabTestController {


    @Autowired
	private LabTestServiceImp service;
	

    @GetMapping("/labTest/{id}")
    public LabTest getById(@PathVariable Long id) {
        return service.getLabTestById(id);
    }
  
	
	
	
	@PostMapping("/save")
	public LabTest save(@RequestBody LabTest LabTest) {
		return service.createLabTest(LabTest);
		
	}
	
	@GetMapping("/get")
	public List<LabTest> get(){
		
		return service.getAllLabTest();
		
	}
	

    @PutMapping("/{id}")
    public LabTest update(@PathVariable Long id, @RequestBody LabTest LabTest){
        return service.updateLabTest(id, LabTest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteLabTest(id);
		System.out.println("aman");
    }
}