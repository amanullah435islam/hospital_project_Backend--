package com.hospital.model;

import com.hospital.common.BaseEntity;
import com.hospital.enums.BloodGroup;
import com.hospital.enums.Gender;
import com.hospital.enums.PatientStatus;
import jakarta.persistence.*;
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
}