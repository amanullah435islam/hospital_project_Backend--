package com.hospital.pharmacy.entity;

import com.hospital.common.entity.BaseEntity;
import com.hospital.medicine.entity.Medicine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "medicine_batches",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_medicine_batch",
                        columnNames = {
                                "medicine_id",
                                "batch_number"
                        }
                )
        },
        indexes = {
                @Index(
                        name = "idx_batch_medicine",
                        columnList = "medicine_id"
                ),
                @Index(
                        name = "idx_batch_expiry",
                        columnList = "expiry_date"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class MedicineBatch extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "medicine_id",
            nullable = false
    )
    private Medicine medicine;

    @Column(
            name = "batch_number",
            nullable = false,
            length = 50
    )
    private String batchNumber;

    @Column(
            name = "purchase_price",
            nullable = false,
            precision = 12,
            scale = 2
    )
    private BigDecimal purchasePrice;

    @Column(
            name = "selling_price",
            nullable = false,
            precision = 12,
            scale = 2
    )
    private BigDecimal sellingPrice;

    @Column(
            nullable = false
    )
    private Integer quantity;

    @Column(
            name = "expiry_date",
            nullable = false
    )
    private LocalDate expiryDate;
}