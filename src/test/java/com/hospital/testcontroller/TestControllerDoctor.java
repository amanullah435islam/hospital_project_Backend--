package com.hospital.testcontroller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// //@AutoConfigureMockMvc  alternative @WebMvcTest

@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerDoctor {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testSaveDoctor() throws Exception {

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

        mockMvc.perform(post("/api/doctor/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetAll() throws Exception {

        mockMvc.perform(get("/api/doctor/getAll"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetById() throws Exception {

        mockMvc.perform(get("/api/doctor/202"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testUpdate() throws Exception {

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

        mockMvc.perform(put("/api/doctor/202")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDelete() throws Exception {

        mockMvc.perform(delete("/api/doctor/152"))
                .andExpect(status().isOk());
    }

}