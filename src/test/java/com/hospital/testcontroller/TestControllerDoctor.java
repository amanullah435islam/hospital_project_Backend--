package com.hospital.testcontroller;


import com.fasterxml.jackson.databind.ObjectMapper;
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
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testSavePatient() throws Exception {

        Patient p = new Patient();

        p.setPatientName("Test User");
        p.setAge(30);
        p.setGender("Male");
        p.setPhone("01700000000");

        mockMvc.perform(post("/api/patient/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetAll() throws Exception {

        mockMvc.perform(get("/api/patient/getAll"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetById() throws Exception {

        mockMvc.perform(get("/api/patient/102"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testUpdate() throws Exception {

        Patient p = new Patient();

        p.setPatientName("Updated Name");
        p.setAge(35);
        p.setGender("Male");
        p.setPhone("01800000000");

        mockMvc.perform(put("/api/patient/102")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDelete() throws Exception {

        mockMvc.perform(delete("/api/patient/2"))
                .andExpect(status().isOk());
    }

}