package com.hospital.appointment.entity;

import com.hospital.common.entity.BaseEntity;
import com.hospital.doctor.entity.Doctor;
import com.hospital.patient.entity.Patient;
import com.hospital.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(
        name = "appointments",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_appointment_code",
                        columnNames = "appointment_code"
                )
        },
        indexes = {
                @Index(
                        name = "idx_appointment_doctor_date",
                        columnList = "doctor_id, appointment_date"
                ),
                @Index(
                        name = "idx_appointment_patient_date",
                        columnList = "patient_id, appointment_date"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Appointment extends BaseEntity {

    @Column(
            name = "appointment_code",
            nullable = false,
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

    @Column(
            name = "appointment_date",
            nullable = false
    )
    private LocalDate appointmentDate;

    @Column(
            name = "start_time",
            nullable = false
    )
    private LocalTime startTime;

    @Column(
            name = "end_time",
            nullable = false
    )
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 30
    )
    private AppointmentType type;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private AppointmentStatus status =
            AppointmentStatus.SCHEDULED;

    @Column(length = 500)
    private String reason;

    @Column(length = 1000)
    private String notes;

    /*
     * User who created the appointment.
     *
     * Example:
     * Receptionist creates appointment.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;
}



//@Entity
//@Table(name = "appointments")
//public class Appointment extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(
//            nullable = false,
//            unique = true,
//            length = 30
//    )
//    private String appointmentCode;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "patient_id",
//            nullable = false
//    )
//    private Patient patient;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "doctor_id",
//            nullable = false
//    )
//    private Doctor doctor;
//
//    private LocalDate appointmentDate;
//
//    private LocalTime appointmentTime;
//
//    private String reason;
//
//    @Enumerated(EnumType.STRING)
//    private AppointmentStatus status;
//
//    private String notes;
//
//    @OneToOne(mappedBy = "appointment", fetch = FetchType.LAZY)
//    private MedicalRecord medicalRecord;
//
//    @OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY)
//    private Set<Prescription> prescriptions = new HashSet<>();
//}