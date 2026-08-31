package com.hospital.prescription.entity;

import com.hospital.common.entity.BaseEntity;
import com.hospital.medicine.entity.Medicine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "prescription_items",
        indexes = {
                @Index(
                        name = "idx_prescription_item_prescription",
                        columnList = "prescription_id"
                ),
                @Index(
                        name = "idx_prescription_item_medicine",
                        columnList = "medicine_id"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class PrescriptionItem extends BaseEntity {

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

    /*
     * Example:
     * 1 tablet
     * 5 ml
     * 2 capsules
     */
    @Column(
            nullable = false,
            length = 100
    )
    private String dosage;

    /*
     * Example:
     * 1-0-1
     * 1-1-1
     * Once daily
     */
    @Column(
            nullable = false,
            length = 100
    )
    private String frequency;

    /*
     * Example:
     * 5 days
     * 7 days
     * 1 month
     */
    @Column(
            nullable = false,
            length = 100
    )
    private String duration;

    /*
     * Oral / IV / IM / Topical etc.
     */
    @Column(
            length = 50
    )
    private String route;

    /*
     * Example:
     * After meal
     * Before meal
     */
    @Column(
            length = 300
    )
    private String instructions;

    @Column(nullable = false)
    private Integer quantity;
}



//@Entity
//@Table(name = "prescription_items")
//public class PrescriptionItem extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "prescription_id",
//            nullable = false
//    )
//    private Prescription prescription;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "medicine_id",
//            nullable = false
//    )
//    private Medicine medicine;
//
//    private String dosage;
//
//    private String frequency;
//
//    private String duration;
//
//    private Integer quantity;
//
//    private String instructions;
//}