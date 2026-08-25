package com.hospital.billing.entity;

import com.hospital.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "payments",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_payment_reference",
                        columnNames = "payment_reference"
                )
        },
        indexes = {
                @Index(
                        name = "idx_payment_invoice",
                        columnList = "invoice_id"
                ),
                @Index(
                        name = "idx_payment_date",
                        columnList = "payment_date"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Payment extends BaseEntity {

    @Column(
            name = "payment_reference",
            nullable = false,
            length = 40
    )
    private String paymentReference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "invoice_id",
            nullable = false
    )
    private Invoice invoice;

    @Column(
            nullable = false,
            precision = 12,
            scale = 2
    )
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "payment_method",
            nullable = false,
            length = 30
    )
    private PaymentMethod paymentMethod;

    @Column(
            name = "payment_date",
            nullable = false
    )
    private LocalDateTime paymentDate;

    @Column(
            length = 500
    )
    private String notes;
}



//    @Entity
//    @Table(name = "payments")
//    public class Payment extends BaseEntity {
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
//        private BigDecimal amount;
//
//        @Enumerated(EnumType.STRING)
//        private PaymentMethod paymentMethod;
//
//        private String transactionId;
//
//        private LocalDateTime paymentDate;
//
//        @Enumerated(EnumType.STRING)
//        private PaymentStatus status;
//    }

