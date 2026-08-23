package com.hospital.appointment.entity;

import com.hospital.common.entity.BaseEntity;
import com.hospital.enums.AppointmentStatus;
import com.hospital.doctor.entity.Doctor;
import com.hospital.medical.entity.MedicalRecord;
import com.hospital.patient.entity.Patient;
import com.hospital.prescription.entity.Prescription;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            unique = true,
            length = 30
    )
    private String appointmentCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "patient_id",
            nullable = false
    )
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "doctor_id",
            nullable = false
    )
    private Doctor doctor;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private String reason;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    private String notes;

    @OneToOne(mappedBy = "appointment", fetch = FetchType.LAZY)
    private MedicalRecord medicalRecord;

    @OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY)
    private Set<Prescription> prescriptions = new HashSet<>();
}