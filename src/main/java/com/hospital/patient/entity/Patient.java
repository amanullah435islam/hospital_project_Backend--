package com.hospital.patient.entity;

import com.hospital.appointment.entity.Appointment;
import com.hospital.common.entity.BaseEntity;
import com.hospital.enums.BloodGroup;
import com.hospital.enums.Gender;
import com.hospital.enums.PatientStatus;
import com.hospital.medical.entity.MedicalRecord;
import com.hospital.prescription.entity.Prescription;
import com.hospital.user.entity.User;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
public class Patient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "patient_code",
            nullable = false,
            unique = true,
            length = 30
    )
    private String patientCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    private String phone;

    private String email;

    private String address;

    private String emergencyContact;

    private String emergencyPhone;

    @Enumerated(EnumType.STRING)
    private PatientStatus status;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<MedicalRecord> medicalRecords = new HashSet<>();

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<Prescription> prescriptions = new HashSet<>();
}