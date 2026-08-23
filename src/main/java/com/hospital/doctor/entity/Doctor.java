package com.hospital.doctor.entity;

import com.hospital.appointment.entity.Appointment;
import com.hospital.common.entity.BaseEntity;
import com.hospital.department.entity.Department;
import com.hospital.enums.DoctorStatus;
import com.hospital.medical.entity.MedicalRecord;
import com.hospital.prescription.entity.Prescription;
import com.hospital.user.entity.User;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "department_id",
            nullable = false
    )
    private Department department;

    @Column(nullable = false, unique = true, length = 30)
    private String doctorCode;

    private String specialization;

    private String qualification;

    @Column(unique = true)
    private String licenseNumber;

    private Integer experienceYears;

    private BigDecimal consultationFee;

    @Enumerated(EnumType.STRING)
    private DoctorStatus status;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private Set<MedicalRecord> medicalRecords = new HashSet<>();

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private Set<Prescription> prescriptions = new HashSet<>();
}