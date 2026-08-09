package com.hospital.testservice;

import com.hospital.model.Doctor;
import com.hospital.serviceimp.DoctorServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestServiceDoctor_alternative {

    @Autowired
    private DoctorServiceImp doctorService;

    @Test
    public void testSaveDoctor() {

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

        Doctor saved = doctorService.createDoctor(p);

        System.out.println("Saved ID: " + saved.getId());
        System.out.println("Saved Name: " + saved.getDoctorName());
    }

    @Test
    public void testGetAll() {
        List<Doctor> list = doctorService.getAllDoctor();
        System.out.println("Total: " + list.size());
    }

    @Test
    public void testGetById() {
        Doctor p = doctorService.getDoctorById(152L);
        System.out.println(p.getDoctorName());
    }

    @Test
    public void testUpdate() {
        Doctor p = new Doctor();

        p.setDoctorCode(152);
        p.setDoctorName("Aman Test");
        p.setAvailability("Morning");
        p.setContact("01425253652");
        p.setDescription("Senior Cardiologist");
        p.setEmail("aman@gmail.com");
        p.setImage("doctor1.jpg");
        p.setRoomNumber("100x");
        p.setSpecialize("Cardiology");

        Doctor updated = doctorService.updateDoctor(152L, p);
        System.out.println("Updated: " + updated.getDoctorName());
    }

    @Test
    public void testDelete() {
        doctorService.deleteDoctor(1001L);
        System.out.println("Deleted Successfully");
    }
}
