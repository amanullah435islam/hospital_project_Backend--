package com.hospital.pharmacy.entity;

import com.hospital.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "medicines",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_medicine_code",
                        columnNames = "medicine_code"
                )
        },
        indexes = {
                @Index(
                        name = "idx_medicine_name",
                        columnList = "name"
                ),
                @Index(
                        name = "idx_medicine_generic_name",
                        columnList = "generic_name"
                ),
                @Index(
                        name = "idx_medicine_status",
                        columnList = "status"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Medicine extends BaseEntity {

    @Column(
            name = "medicine_code",
            nullable = false,
            unique = true,
            length = 30
    )
    private String medicineCode;

    @Column(
            nullable = false,
            length = 150
    )
    private String name;

    @Column(
            name = "generic_name",
            length = 150
    )
    private String genericName;

    @Column(
            length = 100
    )
    private String strength;

    @Column(
            length = 100
    )
    private String dosageForm;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private MedicineStatus status =
            MedicineStatus.ACTIVE;
}