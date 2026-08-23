package com.hospital.model;

import com.hospital.common.entity.BaseEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_items")
public class InvoiceItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "invoice_id",
            nullable = false
    )
    private Invoice invoice;

    @Column(nullable = false)
    private String itemName;

    private String description;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;
}
