package com.hospital.testrepo;

import com.hospital.model.Doctor;
import com.hospital.repository.IDoctorRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

// @SpringBootTest(classes = HospitalApplication.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    public class TestRepoDoctor_alternative {

       @Autowired
       private IDoctorRepo iDoctorRepo;

       @Test
       public void save() {

           Doctor p = new Doctor();

           p.setDoctorCode(1001);
           p.setDoctorName("Aman Test");
           p.setAvailability("Morning");
           p.setContact("01425253652");
           p.setDescription("Senior Cardiologist");
           p.setEmail("aman@gmail.com");
           p.setImage("doctor1.jpg");
           p.setRoomNumber("100x");
           p.setSpecialize("Cardiology");

           Doctor saved = iDoctorRepo.save(p);
           System.out.println("Saved Successfully");

           Optional<Doctor> found =
                   iDoctorRepo.findById(saved.getId());

           assertThat(found).isPresent();
           assertThat(found.get().getDoctorName())
                   .isEqualTo("Aman Test");
       }


       // Test Get All
       @Test
       public void testFindAll() {
           List<Doctor> list = iDoctorRepo.findAll();
           System.out.println("Total Patients: " + list.size());
       }
       // Test Get By ID
       @Test
       public void testFindById() {
           Doctor p = iDoctorRepo.findById(1L).orElse(null);
           System.out.println(p);
       }
       // Test Exists
       @Test
       public void testExists() {
           boolean exists = iDoctorRepo.existsById(1L);
           System.out.println("Exists: " + exists);
       }
       // Test Delete
       @Test
       public void testDelete() {
           iDoctorRepo.deleteById(1L);
           System.out.println("Deleted Successfully");
       }

    }

