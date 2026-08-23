package com.hospital.doctor.entity;

import com.hospital.common.entity.BaseEntity;
import com.hospital.department.entity.Department;
import com.hospital.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(
        name = "doctors",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_doctor_code",
                        columnNames = "doctor_code"
                ),
                @UniqueConstraint(
                        name = "uk_doctor_user",
                        columnNames = "user_id"
                ),
                @UniqueConstraint(
                        name = "uk_doctor_license",
                        columnNames = "license_number"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Doctor extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "department_id",
            nullable = false
    )
    private Department department;

    @Column(
            name = "doctor_code",
            nullable = false,
            length = 30
    )
    private String doctorCode;

    @Column(length = 100)
    private String specialization;

    @Column(length = 255)
    private String qualification;

    @Column(
            name = "license_number",
            length = 100
    )
    private String licenseNumber;

    private Integer experienceYears;

    @Column(
            precision = 10,
            scale = 2
    )
    private BigDecimal consultationFee;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private DoctorStatus status = DoctorStatus.ACTIVE;
}



//@Entity
//@Table(name = "doctors")
//public class Doctor extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "user_id",
//            nullable = false,
//            unique = true
//    )
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "department_id",
//            nullable = false
//    )
//    private Department department;
//
//    @Column(nullable = false, unique = true, length = 30)
//    private String doctorCode;
//
//    private String specialization;
//
//    private String qualification;
//
//    @Column(unique = true)
//    private String licenseNumber;
//
//    private Integer experienceYears;
//
//    private BigDecimal consultationFee;
//
//    @Enumerated(EnumType.STRING)
//    private DoctorStatus status;
//
//    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
//    private Set<Appointment> appointments = new HashSet<>();
//
//    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
//    private Set<MedicalRecord> medicalRecords = new HashSet<>();
//
//    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
//    private Set<Prescription> prescriptions = new HashSet<>();
//}