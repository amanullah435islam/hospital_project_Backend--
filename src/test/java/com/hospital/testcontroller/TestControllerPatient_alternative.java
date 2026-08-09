package com.hospital.testcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.config.JwtAuthenticationFilter;
import com.hospital.controller.DoctorController;
import com.hospital.controller.PatientController;
import com.hospital.dao.UserDAO;
import com.hospital.model.Patient;
import com.hospital.service.UserService;
import com.hospital.serviceimp.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.hospital.serviceimp.*;



//note -0:--->!
//@SpringBootTest
//@AutoConfigureMockMvc

//alternative::::::::::::::::: // tobe @SpringBootTest @AutoConfigureMockMvc use koratai best.

//@WebMvcTest





//note -1:--->!
//@WebMvcTest(
//        controllers = PatientController.class,
//        excludeAutoConfiguration = {
//                      SecurityConfig.class
//        } // Disables security completely for this test

// altenative:::::::::::::

//@WebMvcTest(DoctorController.class)
//@AutoConfigureMockMvc(addFilters = false)




//note -2:--->!
//@AutoConfigureMockMvc(addFilters = false)

//alternative:::::::::::::::::

//@WithMockUser
//or::::::::::::::
// @WithMockUser(username = "admin", roles = {"ADMIN"})





//note -3:--->!
//  @MockBean

// alternative::::::::::::::::: // tobe best   @MockBean use koratai best.

//@MockitoBean

@WebMvcTest(PatientController.class)
class TestControllerPatient_alternative {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //  @MockBean support bellow the 3.4.0 version. I am testing   @MockBean using version 3.3.5
    //project location -> (C:\Users\USER\Desktop\Imran Sir_4-08-2026\demo(1)\demo)
    @MockBean
    private PatientServiceImp patientService;


    private Patient patient;

    @BeforeEach
    void setUp() {

        patient = new Patient();

        patient.setId(1);

        patient.setPatientName("Aman");
    }

    @Test
    void testSave() throws Exception {

        when(patientService.createPatient(any(Patient.class)))
                .thenReturn(patient);

        mockMvc.perform(post("/api/patient/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientName")
                        .value("Aman"));
    }

    @Test
    @WithMockUser
    void testSaveAndGet() throws Exception {

        Patient p = new Patient();

        p.setPatientName("Aman");
        p.setAge(25);

        String response =
                mockMvc.perform(post("/api/patient/save")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(p)))
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        System.out.println(response);
    }

    @Test
    void testGetAll() throws Exception {

        when(patientService.getAllPatient())
                .thenReturn(Collections.singletonList(patient));

        mockMvc.perform(get("/api/patient/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].patientName")
                        .value("Aman"));
    }

    @Test
    void testGetById() throws Exception {

        when(patientService.getPatientById(anyLong()))
                .thenReturn(patient);

        mockMvc.perform(get("/api/patient/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientName")
                        .value("Aman"));
    }

    @Test
    void testDelete() throws Exception {

        doNothing().when(patientService)
                .deletePatient(anyLong());

        mockMvc.perform(delete("/api/patient/1"))
                .andExpect(status().isOk());
    }
}