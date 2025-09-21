package com.hospital.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hospital.dao.DoctorDAO;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.repository.DoctorRepository;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorDAO doctorDAO;

	
    @PostMapping("/doctor")
    public Doctor save(@RequestBody Doctor doctor) {
        return doctorDAO.save(doctor);
    }


    @GetMapping("/doctor")
    public List<Doctor> getAllDoctors() {
        return doctorDAO.getAll();
    }
    
 
    @GetMapping("/doctor/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable(value = "id") long id) {
    	Doctor p = doctorDAO.getDoctorById(id);
        return ResponseEntity.ok().body(p);
    }
    

    @PutMapping("/doctor/{id}")
    public ResponseEntity<Doctor> update(@PathVariable(value = "id") long id,
        	@Validated @RequestBody Doctor doctorDetails) {
        	Doctor doctor = doctorDAO.getDoctorById(id);
        		        
        	doctor.setDoctorCode(doctorDetails.getDoctorCode());
	    	doctor.setDoctorName(doctorDetails.getDoctorName());
	    	doctor.setSpecialize(doctorDetails.getSpecialize());
	    	doctor.setContact(doctorDetails.getContact());
	    	doctor.setAvailability(doctorDetails.getAvailability());
	    	doctor.setEmail(doctorDetails.getEmail());
	    	doctor.setRoomNumber(doctorDetails.getRoomNumber());
	    	doctor.setDescription(doctorDetails.getDescription());
	    	
 
	        final Doctor updatedDoctor = doctorDAO.update(doctor); 
	        return ResponseEntity.ok(updatedDoctor);
    }



    @DeleteMapping("/doctor/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") long id){
    	Doctor d = doctorDAO.getDoctorById(id);
    	doctorDAO.delete(d);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
    
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    @PostMapping("/doctor/saveDoctorWithImage")
    public Doctor saveProductWithImage(@RequestPart("doctor") Doctor doctor,
                                        @RequestPart("image") MultipartFile file) throws IOException {
        
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
        String imagePath = "src/main/resources/static/image/" + file.getOriginalFilename();
        ImageIO.write(image, "jpg", new File(imagePath));
        
        doctor.setImage(file.getOriginalFilename());
        return doctorRepository.save(doctor);
    }
    
}

