package com.hospital.prescription.entity;

import com.hospital.common.entity.BaseEntity;
import com.hospital.medicine.entity.Medicine;
import jakarta.persistence.*;

@Entity
@Table(name = "prescription_items")
public class PrescriptionItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "prescription_id",
            nullable = false
    )
    private Prescription prescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "medicine_id",
            nullable = false
    )
    private Medicine medicine;

    private String dosage;

    private String frequency;

    private String duration;

    private Integer quantity;

    private String instructions;
}