package com.hospital.testcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.config.SecurityConfig;
import com.hospital.controller.PatientController;
import com.hospital.dao.UserDAO;
import com.hospital.model.Patient;
import com.hospital.service.PatientService;
import com.hospital.service.UserService;
import com.hospital.serviceimp.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




// /////////////////////////////ai code a somossa ace ata r kora jabe na...................
// ////////////////////////////////@AutoConfigureMockMvc  alternative @WebMvcTest...............




//@WebMvcTest(
//        controllers = PatientController.class,
//        excludeAutoConfiguration = {SecurityConfig.class} // Disables security completely for this test
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(PatientController.class)
//@AutoConfigureTestEntityManager
//class PatientControllerTest3 {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    //@MockitoBean
//    @Mock
//    private PatientService patientService;
//
//    private Patient patient;
//
//    @BeforeEach
//    void setUp() {
//        patient = new Patient();
//        patient.setId(1);
//        patient.setPatientName("Aman");
//    }
//
//    @Test
//    void testSave() throws Exception {
//        when(patientService.createPatient(any(Patient.class))).thenReturn(patient);
//
//        mockMvc.perform(post("/api/patient/save")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(patient)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.patientName").value("Aman"));
//    }
//
//    @Test
//    void testGetAll() throws Exception {
//        when(patientService.getAllPatient()).thenReturn(Collections.singletonList(patient));
//
//        mockMvc.perform(get("/api/patient/getAll"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].patientName").value("Aman"));
//    }
//
//    @Test
//    void testGetById() throws Exception {
//        when(patientService.getPatientById(anyLong())).thenReturn(patient);
//
//        mockMvc.perform(get("/api/patient/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.patientName").value("Aman"));
//    }
//}





import com.hospital.config.JwtAuthenticationFilter;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;


//@WebMvcTest(
//        controllers = PatientController.class,
//        excludeAutoConfiguration = {
//                SecurityConfig.class
//        }
//)

// //alternativa::::::::::



@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
class PatientControllerTest3 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockitoBean
    private PatientServiceImp patientService;


    @MockitoBean
    private AppointmentServiceImp appointmentService;

    @MockitoBean
    private DoctorServiceImp doctorService;

    @MockitoBean
    private AppPaymentServiceImp appPaymentServiceImp;

    @MockitoBean
    private LabTestServiceImp labTestServiceImp;


    @MockitoBean
    private MedicineServiceImp medicineServiceImp;

    @MockitoBean
    private PrescriptionServiceImp prescriptionServiceImp;

    @MockitoBean
    private TestPaymentServiceImp testPaymentServiceImp;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private UserDAO userDAO;



    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

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