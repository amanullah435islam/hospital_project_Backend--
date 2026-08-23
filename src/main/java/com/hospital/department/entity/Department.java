package com.hospital.department.entity;

import com.hospital.common.entity.BaseEntity;
import com.hospital.enums.DepartmentStatus;
import com.hospital.doctor.entity.Doctor;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private DepartmentStatus status;

    @OneToMany(mappedBy = "department")
    private Set<Doctor> doctors = new HashSet<>();
}
