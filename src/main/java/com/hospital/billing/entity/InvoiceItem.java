package com.hospital.billing.entity;

import com.hospital.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(
        name = "invoice_items",
        indexes = {
                @Index(
                        name = "idx_invoice_item_invoice",
                        columnList = "invoice_id"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class InvoiceItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "invoice_id",
            nullable = false
    )
    private Invoice invoice;

    @Column(
            nullable = false,
            length = 200
    )
    private String description;

    @Column(
            nullable = false,
            precision = 12,
            scale = 2
    )
    private BigDecimal unitPrice;

    @Column(
            nullable = false
    )
    private Integer quantity;

    public BigDecimal getTotal() {

        return unitPrice.multiply(
                BigDecimal.valueOf(quantity)
        );
    }
}


//    @Entity
//    @Table(name = "invoice_items")
//    public class InvoiceItem extends BaseEntity {
//
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(
//                name = "invoice_id",
//                nullable = false
//        )
//        private Invoice invoice;
//
//        @Column(nullable = false)
//        private String itemName;
//
//        private String description;
//
//        private Integer quantity;
//
//        private BigDecimal unitPrice;
//
//        private BigDecimal totalPrice;
//    }


