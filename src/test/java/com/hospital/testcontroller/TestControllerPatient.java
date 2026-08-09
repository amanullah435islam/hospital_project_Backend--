package com.hospital.testcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.Patient;
import com.hospital.serviceimp.PatientServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



// //@AutoConfigureMockMvc  alternative @WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerPatient {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


 @MockitoBean
    private PatientServiceImp patientService;

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


// //or::::::::::::::::::::::::::::::::::::::::::::::::


    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testSavePatient1() throws Exception {

        Patient p = new Patient();
        p.setPatientName("Test User");
        p.setAge(30);

        mockMvc.perform(post("/api/patient/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientName")
                        .value("Test User"));
    }


    // details::::::::::::::::::::::::

//5. Mock HTTP request
//    mockMvc.perform(post("/api/patient/save")
//    Real HTTP call na
//    MockMvc fake HTTP request simulate kore
//    Endpoint: /api/patient/save
//    Method: POST

//6. Content type set
//            .contentType(MediaType.APPLICATION_JSON)
//    Backend bujhe request JSON format e ashche


//7. Object → JSON convert
//.content(objectMapper.writeValueAsString(p))
//    Patient object → JSON string convert hoy


//8. Expect result
//.andExpect(status().isOk());
//    Expect kore API response 200 OK dibe
//    Mane save successful


    // //all process::::::::::::::::::::::::
//    Full Flow

//   est Method
//   ↓
//   Mock User Login (ADMIN)
//   ↓
//   Create Patient Object
//   ↓
//   Convert to JSON
//   ↓
//   POST /api/patient/save
//   ↓
//   Controller receives request
//   ↓
//   Service save data
//   ↓
//   Database insert
//   ↓
//   Return HTTP 200 OK



    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetAll() throws Exception {

        mockMvc.perform(get("/api/patient/getAll"))
                .andExpect(status().isOk());
    }


// //or::::::::::::::::::::::::::::::::::::::::::::::::


//   // Better Version (Professional)
    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testGetAll1() throws Exception {

        mockMvc.perform(get("/api/patient/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    // //or::::::::::::::::::::::::::::::::::::::::::::::::


//  //  Better Version (Professional)
    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testGetAll2() throws Exception {

        mockMvc.perform(get("/api/patient/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].patientName").exists());;
    }

//    Full Flow

//   Test Method
//   ↓
//   Mock User Login (ADMIN)
//   ↓
//   GET /api/patient/getAll call
//   ↓
//   Controller execute
//   ↓
//   Service getAllPatient()
//   ↓
//   Database fetch all patients
//   ↓
//   Return response
//   ↓
//   Assert HTTP 200 OK



//3. Mock HTTP GET request
//mockMvc.perform(get("/api/patient/getAll"))
//
//    Ekhane:
//
//    Real browser request na
//    MockMvc fake HTTP GET request simulate kore
//
//    Endpoint call hocche:
//
//            /api/patient/getAll


//4. Expect response status
//            .andExpect(status().isOk());
//    Expect kore HTTP response 200 OK ashbe
//    Mane API successfully run korse



    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetById() throws Exception {

        mockMvc.perform(get("/api/patient/102"))
                .andExpect(status().isOk());
    }


// //or::::::::::::::::::::::::::::::::::::::::::::::::

//    //Better Version (Recommended)
    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testGetById1() throws Exception {

        mockMvc.perform(get("/api/patient/102"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientName").exists());
    }


    // //or::::::::::::::::::::::::::::::::::::::::::::::::


//   // Better Version (Recommended)
    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testGetById2() throws Exception {

        mockMvc.perform(get("/api/patient/102"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(102));;
    }

//
//   Test Method
//   ↓
//   Mock User Login (ADMIN)
//   ↓
//   GET /api/patient/102 request
//   ↓
//   Controller receive request
//   ↓
//   Service getPatientById(102)
//   ↓
//   Repository findById(102)
//   ↓
//   Database query execute
//   ↓
//   Patient return
//   ↓
//   HTTP 200 OK return












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


// //or::::::::::::::::::::::::::::::::::::::::::::::::

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testUpdate1() throws Exception {

        Patient p = new Patient();
        p.setPatientName("Updated Name");
        p.setAge(35);

        mockMvc.perform(put("/api/patient/102")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientName")
                        .value("Updated Name"));
    }

    // //or::::::::::::::::::::::::::::::::::::::::::::::::

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void testUpdate2() throws Exception {

        Patient p = new Patient();
        p.setPatientName("Updated Name");
        p.setAge(35);

        mockMvc.perform(put("/api/patient/102")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value(35));;
    }

//5. Mock HTTP PUT request
//mockMvc.perform(put("/api/patient/102")
//    Real HTTP request na
//    MockMvc fake PUT request simulate kore
//
//    Endpoint:
//
//            /api/patient/102
//
//            👉 Mane:
//
//            102 = patient ID jake update kora hocche


//    Full Flow

//   Test Method
//   ↓
//   Mock User Login (ADMIN)
//   ↓
//   Create Updated Patient Object
//   ↓
//   Convert to JSON
//   ↓
//   PUT /api/patient/102
//            ↓
//   Controller receives request
//   ↓
//   Service updatePatient(102, data)
//   ↓
//   Find existing record
//   ↓
//   Update fields
//   ↓
//   Save to DB
//   ↓
//   Return updated object
//   ↓
//   HTTP 200 OK


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDelete() throws Exception {

        mockMvc.perform(delete("/api/patient/2"))
                .andExpect(status().isOk());
    }


    // //or::::::::::::::::::::::::::::::::::::::::::::::::


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testDeletePatient() throws Exception {

        // Arrange (mock behavior)
        doNothing().when(patientService).deletePatient(102L);

        // Act + Assert
        mockMvc.perform(delete("/api/patient/102"))
                .andExpect(status().isOk());
    }


    // //or::::::::::::::::::::::::::::::::::::::::::::::::


    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeletePatient2() throws Exception {

        // given
        doNothing().when(patientService).deletePatient(ArgumentMatchers.eq(2L));

        // when + then
        mockMvc.perform(delete("/api/patient/{id}", 2L))
                .andExpect(status().isOk());
    }


//    //✔ Best testing stack:::::::::::::::::::::::::::
//    Layer              ---     	 Tool

//    Controller test	   ---       @WebMvcTest + MockMvc
//    Service test	       ---       @ExtendWith(MockitoExtension.class)
//    Repository test	    ---      @DataJpaTest


}