package com.hospital.controller;

import java.util.List;

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
import com.hospital.model.Medicine;
import com.hospital.serviceimp.MedicineServiceImp;

import lombok.RequiredArgsConstructor;


//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/medicine")


//@RequiredArgsConstructor
public class MedicineController {

    @Autowired
	private MedicineServiceImp service;

    @GetMapping("/medicine/{id}")
    public Medicine getById(@PathVariable Long id) {
        return service.getMedicineById(id);
    }
  
	@PostMapping("/save")
	public Medicine save(@RequestBody Medicine Medicine) {
		return service.createMedicine(Medicine);
		
	}
	
	@GetMapping("/get")
	public List<Medicine> get(){
		
		return service.getAllMedicine();
		
	}
	

    @PutMapping("/{id}")
    public Medicine update(@PathVariable Long id, @RequestBody Medicine Medicine){
        return service.updateMedicine(id, Medicine);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteMedicine(id);
		System.out.println("aman");
    }

   
}

