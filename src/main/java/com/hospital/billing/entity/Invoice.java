package com.hospital.billing.entity;

import com.hospital.common.entity.BaseEntity;
import com.hospital.patient.entity.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "invoices",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_invoice_number",
                        columnNames = "invoice_number"
                )
        },
        indexes = {
                @Index(
                        name = "idx_invoice_patient",
                        columnList = "patient_id"
                ),
                @Index(
                        name = "idx_invoice_date",
                        columnList = "invoice_date"
                ),
                @Index(
                        name = "idx_invoice_status",
                        columnList = "status"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Invoice extends BaseEntity {

    @Column(
            name = "invoice_number",
            nullable = false,
            length = 30
    )
    private String invoiceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "patient_id",
            nullable = false
    )
    private Patient patient;

    @Column(
            name = "invoice_date",
            nullable = false
    )
    private LocalDate invoiceDate;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 30
    )
    private InvoiceStatus status =
            InvoiceStatus.UNPAID;

    @Column(
            precision = 12,
            scale = 2
    )
    private BigDecimal discount =
            BigDecimal.ZERO;

    @Column(
            precision = 12,
            scale = 2
    )
    private BigDecimal tax =
            BigDecimal.ZERO;

    @Column(
            length = 1000
    )
    private String notes;

    @OneToMany(
            mappedBy = "invoice",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<InvoiceItem> items =
            new ArrayList<>();


    public void addItem(
            InvoiceItem item
    ) {

        items.add(item);

        item.setInvoice(this);
    }


    public void removeItem(
            InvoiceItem item
    ) {

        items.remove(item);

        item.setInvoice(null);
    }
}


//    @Entity
//    @Table(name = "invoices")
//    public class Invoice extends BaseEntity {
//
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
//
//        @Column(
//                nullable = false,
//                unique = true,
//                length = 30
//        )
//        private String invoiceNumber;
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(
//                name = "patient_id",
//                nullable = false
//        )
//        private Patient patient;
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "appointment_id")
//        private Appointment appointment;
//
//        private BigDecimal subtotal;
//
//        private BigDecimal discount;
//
//        private BigDecimal tax;
//
//        private BigDecimal totalAmount;
//
//        @Enumerated(EnumType.STRING)
//        private InvoiceStatus status;
//
//        private LocalDateTime issuedAt;
//    }


