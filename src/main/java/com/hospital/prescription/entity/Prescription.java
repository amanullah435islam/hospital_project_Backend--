package com.hospital.prescription.entity;

import com.hospital.common.entity.BaseEntity;
import com.hospital.doctor.entity.Doctor;
import com.hospital.medical.entity.MedicalRecord;
import com.hospital.patient.entity.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "prescriptions",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_prescription_code",
                        columnNames = "prescription_code"
                ),
                @UniqueConstraint(
                        name = "uk_prescription_medical_record",
                        columnNames = "medical_record_id"
                )
        },
        indexes = {
                @Index(
                        name = "idx_prescription_patient",
                        columnList = "patient_id"
                ),
                @Index(
                        name = "idx_prescription_doctor",
                        columnList = "doctor_id"
                ),
                @Index(
                        name = "idx_prescription_date",
                        columnList = "prescribed_date"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Prescription extends BaseEntity {

    @Column(
            name = "prescription_code",
            nullable = false,
            length = 30
    )
    private String prescriptionCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "medical_record_id",
            nullable = false,
            unique = true
    )
    private MedicalRecord medicalRecord;

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
            name = "prescribed_date",
            nullable = false
    )
    private LocalDate prescribedDate;

    @Column(
            length = 2000
    )
    private String notes;

    /*
     * One Prescription → Many PrescriptionItems
     */
    @OneToMany(
            mappedBy = "prescription",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PrescriptionItem> items =
            new ArrayList<>();


    public void addItem(
            PrescriptionItem item
    ) {

        items.add(item);

        item.setPrescription(this);
    }


    public void removeItem(
            PrescriptionItem item
    ) {

        items.remove(item);

        item.setPrescription(null);
    }


//    Entry level:
//    @Enumerated(EnumType.STRING)
//    @Column(
//            name = "status",
//            nullable = false,
//            length = 20
//    )
//    private PrescriptionStatus status =
//            PrescriptionStatus.ACTIVE;


//    Senior level:
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PrescriptionStatus status;
}



//@Entity
//@Table(name = "prescriptions")
//public class Prescription extends BaseEntity {
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
//    private String prescriptionCode;
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
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "appointment_id",
//            nullable = false
//    )
//    private Appointment appointment;
//
//    private LocalDateTime prescriptionDate;
//
//    @Column(columnDefinition = "TEXT")
//    private String notes;
//
//    @Enumerated(EnumType.STRING)
//    private PrescriptionStatus status;
//}
