package com.hospital.testrepo;

import com.hospital.HospitalApplication;
import com.hospital.model.Patient;
import com.hospital.repository.IPatientRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = HospitalApplication.class)
public class TestRepoPatient {

    @Autowired
    private IPatientRepo patientRepo;

    @Test
    public void save() {

        Patient p = new Patient();

        p.setPatientCode(1001);
        p.setPatientName("Aman Test");
        p.setAge(25);
        p.setGender("Male");
        p.setPhone("01700000000");
        p.setLastVisit(new Date());
        p.setDob(new Date());


        Patient saved = patientRepo.save(p);

        System.out.println("Saved Successfully");
        assertThat(saved.getId()).isNotNull();

        Optional<Patient> found =
                patientRepo.findById(saved.getId());

        assertThat(found).isPresent();

        assertThat(found.get().getPatientName())
                .isEqualTo("Aman Test");
    }

    //   // save method all process:::::::::::::::::::::
//    Create Patient Object
//        ↓
//    Set Data
//        ↓
//    Save to DB
//        ↓
//    Get Generated ID
//        ↓
//    Find By ID
//        ↓
//    Check Data Exists
//        ↓
//    Verify Name



// //specific method details explanation:::::::::::::
//    Concept	                      Meaning

//    @Test	        ---              JUnit test
//    save()	       ---            insert/update
//    findById()	     ---          select by id
//    Optional	          ---         null safety
//    assertThat()	        ---       verification



    // Test Get All
    @Test
    public void testFindAll() {
        List<Patient> list = patientRepo.findAll();
        System.out.println("Total Patients: " + list.size());
    }


    //   // getall method all process:::::::::::::::::::::

//    Repository
//    ↓
//    JPA
//    ↓
//    SQL Query
//    ↓
//    SELECT * FROM patient
//    ↓
//    Data Convert to Objects
//    ↓
//    Return List<Patient>

    // //or:::::::::::::::::::::::::
    @Test
    public void testFindAll1() {

        List<Patient> list = patientRepo.findAll();

        assertThat(list).isNotEmpty();

        assertThat(list.size()).isGreaterThan(0);
    }

// //or:::::::::::::::::::::::::::::::::::::

    @Test
    void testFindAllPatients() {

        List<Patient> patients = patientRepo.findAll();

        assertThat(patients).isNotNull();

        assertThat(patients.size()).isGreaterThan(0);

        patients.forEach(p ->
                System.out.println(p.getPatientName()));
    }

    //   // getall method all process:::::::::::::::::::::

//    Test Start
//    ↓
//    Repository call
//    ↓
//    JPA SQL generate
//    ↓
//    Database query execute
//    ↓
//    Result আসে
//    ↓
//    Patient object তৈরি হয়
//    ↓
//    List return হয়
//    ↓
//    Assertions check হয়
//    ↓
//    Console print হয়


//    //Real Internal:::::::::::::

//    Test Method
//    ↓
//    Repository
//    ↓
//    Spring Data JPA
//    ↓
//    Hibernate
//    ↓
//    SQL Query
//    ↓
//    MySQL Database
//    ↓
//    ResultSet
//    ↓
//    Entity Mapping
//    ↓
//    Patient Objects
//    ↓
//    List<Patient>
//    ↓
//    Assertions


    // //or::::::::::::::::::::::::::::::::::::::::::::::::
    @Test
    void testFindAllPatients1() {

        List<Patient> patients = patientRepo.findAll();

        assertThat(patients)
                .isNotNull()
                .isNotEmpty();

        patients.forEach(patient -> {

            assertThat(patient.getPatientName())
                    .isNotBlank();

            System.out.println(patient.getPatientName());
        });
    }



    // Test Get By ID
    @Test
    public void testFindById() {
        Patient p = patientRepo.findById(1L).orElse(null);
        System.out.println(p);
    }

    // //or::::::::::::::::::::::::::::::::::::::::::::::::

//    //🚀 Better Professional Version
    @Test
    void testFindById1() {

        Patient patient =
                patientRepo.findById(102L).orElse(null);

        assertThat(patient).isNotNull();

        assertThat(patient.getPatientName())
                .isEqualTo("Aman");
    }


    // //or::::::::::::::::::::::::::::::::::::::::::::::::

//🔥 আরো safe version
    @Test
    void testFindById2() {

        Optional<Patient> optionalPatient =
                patientRepo.findById(102L);

        assertThat(optionalPatient).isPresent();

        Patient patient = optionalPatient.get();

        assertThat(patient.getPatientName())
                .isEqualTo("Aman");
    }

    //   // getall method all process:::::::::::::::::::::

//    Test Method
//    ↓
//    Repository.findById(1L)
//    ↓
//    Hibernate
//    ↓
//    SQL Query
//    ↓
//    SELECT * FROM patient WHERE id=1
//    ↓
//    Database
//    ↓
//    Row Found
//    ↓
//    Entity Object তৈরি
//    ↓
//    Optional<Patient>
//    ↓
//    orElse(null)
//    ↓
//    Patient Object


//🔥 Important          ---            Concepts

//    Concept	        ---             Meaning
//    findById()        ---         	ID দিয়ে search
//    1L	            ---             Long datatype
//    Optional	        ---             null-safe wrapper
//    orElse(null)	    ---             না পেলে null
//     System.out.println()	  ---       console output


    // Test Exists
    @Test
    public void testExists() {
        boolean exists = patientRepo.existsById(1L);
        System.out.println("Exists: " + exists);
    }



//    //🚀 Better Professional Version
    @Test
    void testExists1() {

        boolean exists =
                patientRepo.existsById(102L);

        assertThat(exists).isTrue();
    }

// //🔥 Internal Flow Diagram

//    Test Method
//    ↓
//    Repository.existsById(1L)
//    ↓
//    Spring Data JPA
//    ↓
//    Hibernate
//    ↓
//    SQL Query
//    ↓
//    Database
//    ↓
//    true / false
//    ↓
//    boolean variable







    // Test Delete
    @Test
    public void testDelete() {
        patientRepo.deleteById(1L);
        System.out.println("Deleted Successfully");
    }


    // //or::::::::::::::::::::::::::::::::::::::::::::::::

//    //🚀 Better Professional Version
    @Test
    void testDelete1() {

        patientRepo.deleteById(1L);

        boolean exists =
                patientRepo.existsById(1L);

        assertThat(exists).isFalse();
    }


//    Test Method
//    ↓
//    deleteById(1L)
//    ↓
//    Spring Data JPA
//    ↓
//    Hibernate
//    ↓
//    SQL Query
//    ↓
//    DELETE FROM patient WHERE id=1
//    ↓
//    Database delete row
//    ↓
//    existsById()
//    ↓
//    Check remaining data
//    ↓
//    Assertion verify


//   // 🧠 Important Concepts

//    Concept	                  ---            Meaning
//    deleteById()	             ---             ID দিয়ে delete
//    DELETE SQL	             ---             row remove
//    existsById()	             ---             verify deletion
//    assertThat().isFalse()	  ---            deletion confirmed


}
