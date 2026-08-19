package com.hospital.model;

import com.hospital.common.BaseEntity;
import com.hospital.enums.MedicineStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "medicines")
public class Medicine extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    private String genericName;

    private String strength;

    private String dosageForm;

    private BigDecimal unitPrice;

    private Integer stockQuantity;

    private Integer reorderLevel;

    @Enumerated(EnumType.STRING)
    private MedicineStatus status;
}
