package com.hospital.model;

import com.hospital.common.BaseEntity;
import com.hospital.enums.DepartmentStatus;
import jakarta.persistence.*;

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

}
