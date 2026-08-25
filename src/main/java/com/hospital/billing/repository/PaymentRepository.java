package com.hospital.billing.repository;

import com.hospital.billing.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



//public interface PaymentRepository
//        extends JpaRepository<Payment, Long> {
//
//    boolean existsByPaymentReference(
//            String paymentReference
//    );
//
//    List<Payment> findByInvoiceIdOrderByPaymentDateDesc(
//            Long invoiceId
//    );
//
//    BigDecimal findTotalPaidByInvoiceId(
//            Long invoiceId
//    );
//}


public interface PaymentRepository
        extends JpaRepository<Payment, Long> {

    boolean existsByPaymentReference(
            String paymentReference
    );

    List<Payment> findByInvoiceIdOrderByPaymentDateDesc(
            Long invoiceId
    );

    @Query("""
            SELECT COALESCE(SUM(p.amount), 0)
            FROM Payment p
            WHERE p.invoice.id = :invoiceId
            """)
    BigDecimal findTotalPaidByInvoiceId(
            @Param("invoiceId")
            Long invoiceId
    );
}