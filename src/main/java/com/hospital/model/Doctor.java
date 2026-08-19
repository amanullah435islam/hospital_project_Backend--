package com.hospital.model;

import com.hospital.common.BaseEntity;
import com.hospital.enums.DoctorStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;

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
}