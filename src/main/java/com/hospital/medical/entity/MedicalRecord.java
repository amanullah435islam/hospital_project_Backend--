package com.hospital.medical.entity;

import com.hospital.appointment.entity.Appointment;
import com.hospital.common.entity.BaseEntity;
import com.hospital.doctor.entity.Doctor;
import com.hospital.patient.entity.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(
        name = "medical_records",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_medical_record_code",
                        columnNames = "record_code"
                ),
                @UniqueConstraint(
                        name = "uk_medical_record_appointment",
                        columnNames = "appointment_id"
                )
        },
        indexes = {
                @Index(
                        name = "idx_medical_record_patient",
                        columnList = "patient_id"
                ),
                @Index(
                        name = "idx_medical_record_doctor",
                        columnList = "doctor_id"
                ),
                @Index(
                        name = "idx_medical_record_visit_date",
                        columnList = "visit_date"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class MedicalRecord extends BaseEntity {

    @Column(
            name = "record_code",
            nullable = false,
            length = 30
    )
    private String recordCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "appointment_id",
            nullable = false,
            unique = true
    )
    private Appointment appointment;

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
            name = "visit_date",
            nullable = false
    )
    private LocalDate visitDate;

    @Column(
            name = "chief_complaint",
            length = 1000
    )
    private String chiefComplaint;

    @Column(
            length = 2000
    )
    private String symptoms;

    @Column(
            length = 2000
    )
    private String diagnosis;

    @Column(
            name = "clinical_notes",
            length = 5000
    )
    private String clinicalNotes;

    @Column(
            name = "treatment_plan",
            length = 3000
    )
    private String treatmentPlan;

    @Column(
            name = "follow_up_date"
    )
    private LocalDate followUpDate;
}



//@Entity
//@Table(name = "medical_records")
//public class MedicalRecord extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
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
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "appointment_id",
//            nullable = false,
//            unique = true
//    )
//    private Appointment appointment;
//
//    @Column(columnDefinition = "TEXT")
//    private String diagnosis;
//
//    @Column(columnDefinition = "TEXT")
//    private String symptoms;
//
//    @Column(columnDefinition = "TEXT")
//    private String notes;
//
//    private LocalDateTime recordDate;
//}
