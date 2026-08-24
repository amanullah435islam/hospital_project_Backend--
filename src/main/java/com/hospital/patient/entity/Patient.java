package com.hospital.patient.entity;

import com.hospital.common.entity.BaseEntity;
import com.hospital.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(
        name = "patients",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_patient_code",
                        columnNames = "patient_code"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Patient extends BaseEntity {

    @Column(
            name = "patient_code",
            nullable = false,
            length = 30
    )
    private String patientCode;

    /*
     * Patient login account optional.
     *
     * Example:
     * Hospital registered patient
     * may not have an online account.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            unique = true
    )
    private User user;

    @Column(
            name = "first_name",
            nullable = false,
            length = 100
    )
    private String firstName;

    @Column(
            name = "last_name",
            length = 100
    )
    private String lastName;

    @Column(
            name = "date_of_birth",
            nullable = false
    )
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private BloodGroup bloodGroup;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MaritalStatus maritalStatus;

    @Column(
            nullable = false,
            length = 20
    )
    private String phone;

    @Column(length = 150)
    private String email;

    @Column(length = 500)
    private String address;

    @Column(
            name = "emergency_contact_name",
            length = 150
    )
    private String emergencyContactName;

    @Column(
            name = "emergency_contact_phone",
            length = 20
    )
    private String emergencyContactPhone;

    @Column(length = 100)
    private String occupation;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private PatientStatus status = PatientStatus.ACTIVE;
}



//@Entity
//@Table(
//        name = "patients",
//        uniqueConstraints = {
//                @UniqueConstraint(
//                        name = "uk_patient_code",
//                        columnNames = "patient_code"
//                )
//        }
//)
//public class Patient extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(
//            name = "patient_code",
//            nullable = false,
//            unique = true,
//            length = 30
//    )
//    private String patientCode;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "user_id",
//            nullable = false,
//            unique = true
//    )
//    private User user;
//
//    @Column(nullable = false)
//    private String firstName;
//
//    private String lastName;
//
//    private LocalDate dateOfBirth;
//
//    @Enumerated(EnumType.STRING)
//    private Gender gender;
//
//    @Enumerated(EnumType.STRING)
//    private BloodGroup bloodGroup;
//
//    private String phone;
//
//    private String email;
//
//    private String address;
//
//    private String emergencyContact;
//
//    private String emergencyPhone;
//
//    @Enumerated(EnumType.STRING)
//    private PatientStatus status;
//
//    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
//    private Set<Appointment> appointments = new HashSet<>();
//
//    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
//    private Set<MedicalRecord> medicalRecords = new HashSet<>();
//
//    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
//    private Set<Prescription> prescriptions = new HashSet<>();
//}