package com.hospital.testservice;

import com.hospital.repository.IPatientRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.hospital.model.Patient;
import com.hospital.service.PatientService;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestServicePatient {

    @Autowired
    private PatientService patientService;

    @Autowired
    private IPatientRepo patientRepo;

    @Test
    public void testSavePatient() {

        Patient p = new Patient();
        p.setPatientName("Rakib");
        p.setAge(25);
        p.setGender("Male");
        p.setPhone("01700000000");
        p.setPatientCode(200);
        p.setDob(new Date());
        p.setLastVisit(new Date());

        Patient saved = patientService.createPatient(p);

        System.out.println("Saved ID: " + saved.getId());
        System.out.println("Saved Name: " + saved.getPatientName());
    }


 // //   or::::::::::::::::::::::::::::
 @Test
 public void testSavePatient1() {

     Patient p = new Patient();

     p.setPatientName("Rakib");
     p.setAge(25);

     Patient saved = patientService.createPatient(p);

     assertNotNull(saved);

     assertEquals("Rakib", saved.getPatientName());
 }



// // all process::::::::::::::::::::::

//    Test Method
//    ↓
//    Patient Object Create
//    ↓
//    Data Set
//    ↓
//    patientService.createPatient()
//    ↓
//    Repository save()
//    ↓
//    Database Save
//    ↓
//    Saved Object Return
//    ↓
//    Print Result
//





    @Test
    public void testGetAll() {
        List<Patient> list = patientService.getAllPatient();
        System.out.println("Total: " + list.size());
    }

    // //or::::::::::::::::::::::::::::::::::::::::::::::::

    @Test
    public void testGetAll1() {

        List<Patient> list = patientService.getAllPatient();

        assertNotNull(list);

        assertTrue(list.size() > 0);
    }

    // //or::::::::::::::::::::::::::::::::::::::::::::::::

    @Test
    public void testGetAll2() {

        List<Patient> list = patientService.getAllPatient();

        assertEquals("Rakib",
                list.get(0).getPatientName());
    }

    // //or::::::::::::::::::::::::::::::::::::::::::::::::

    @Test
    public void testGetAll3() {

        List<Patient> list = patientService.getAllPatient();

        boolean found = list.stream()
                .anyMatch(p ->
                        p.getPatientName().equals("Rakib"));

        assertTrue(found);
    }

    // //or::::::::::::::::::::::::::::::::::::::::::::::::

    @Test
    public void testGetAllPatients() {

        List<Patient> patients =
                patientService.getAllPatient();

        assertNotNull(patients);

        assertTrue(patients.size() > 0);
    }

//    Best Practice:->
//    Usually service test e use hoy:
//
//    assertNotNull()
//    assertEquals()
//    assertTrue()
//    assertFalse()
//    assertThrows()


    // //all process::::::::::::::::::::::::::::

//    Test Method
//    ↓
//    patientService.getAllPatient()
//    ↓
//    patientRepo.findAll()
//    ↓
//    Database Query Run
//    ↓
//    All Patient List Return
//



    @Test
    public void testGetById() {
        Patient p = patientService.getPatientById(102L);
        System.out.println(p.getPatientName());
    }

    // //or::::::::::::::::::::::::::::::::::::::::::::::::


//    Safer Version

    @Test
    public void testGetById1() {

        Patient p =
                patientService.getPatientById(102L);

        assertNotNull(p);

        System.out.println(p.getPatientName());
    }


    // //or::::::::::::::::::::::::::::::::::::::::::::::::


//    Better Professional Testing

    @Test
    public void testGetById2() {

        Patient p =
                patientService.getPatientById(102L);

        assertNotNull(p);

        assertEquals("Rakib",
                p.getPatientName());
    }


// //or::::::::::::::::::::::::::::::::::::::::::::::::


//    Best Practice (Optional)

    @Test
    public void testGetById3() {

        Optional<Patient> optional =
                patientRepo.findById(102L);

        assertTrue(optional.isPresent());

        Patient p = optional.get();

    }



//    Test Method
//    ↓
//    patientService.getPatientById(102L)
//    ↓
//    patientRepo.findById(102L)
//    ↓
//    Database Query
//    ↓
//    Patient Return



    @Test
    public void testUpdate() {
        Patient p = new Patient();
        p.setPatientName("Updated Name");
        p.setAge(30);
        p.setGender("Male");
        p.setPhone("01700000000");
        p.setPatientCode(100);
        p.setLastVisit(new Date());

        Patient updated = patientService.updatePatient(102L, p);
        System.out.println("Updated: " + updated.getPatientName());
    }


    // //or::::::::::::::::::::::::::::::::::::::::::::::::



//   // Better Version (Professional)
    @Test
    public void testUpdate1() {

        Patient p = new Patient();
        p.setPatientName("Updated Name");
        p.setAge(30);

        Patient updated =
                patientService.updatePatient(102L, p);

        assertNotNull(updated);

        assertEquals("Updated Name",
                updated.getPatientName());
    }


// // all process::::::::::::::::::::::::::

//   Test Method
//   ↓
//   Create new Patient (updated data)
//   ↓
//   patientService.updatePatient(102L, p)
//   ↓
//   Find existing patient by ID
//   ↓
//   Update fields
//   ↓
//   Save to database
//   ↓
//   Return updated object
//   ↓
//   Print result




    @Test
    public void testDelete() {
        patientService.deletePatient(2L);
        System.out.println("Deleted Successfully");
    }


//   Test Method
//   ↓
//   patientService.deletePatient(2L)
//   ↓
//   Find patient by ID
//   ↓
//   If exists → delete
//   ↓
//   Remove from database


// //or::::::::::::::::::::::::::::::::::::::::::::::::


//    //Better Testing (Recommended)
    @Test
    public void testDelete1() {

        patientService.deletePatient(2L);

        boolean exists =
                patientRepo.existsById(2L);

        assertFalse(exists);
    }

// //all process:::::::::::::::::::::::::::::

//    Test Method
//    ↓
//    deletePatient(2L)
//    ↓
//    Find patient
//    ↓
//    Delete from DB
//    ↓
//    Check existsById
//    ↓
//    Should return false


}