package com.hospital.prescription.entity;

import com.hospital.appointment.entity.Appointment;
import com.hospital.common.entity.BaseEntity;
import com.hospital.doctor.entity.Doctor;
import com.hospital.enums.PrescriptionStatus;
import com.hospital.patient.entity.Patient;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
public class Prescription extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            unique = true,
            length = 30
    )
    private String prescriptionCode;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "appointment_id",
            nullable = false
    )
    private Appointment appointment;

    private LocalDateTime prescriptionDate;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Enumerated(EnumType.STRING)
    private PrescriptionStatus status;
}
