package com.hospital.billing.repository;

import com.hospital.billing.entity.Invoice;
import com.hospital.billing.entity.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvoiceRepository
        extends JpaRepository<Invoice, Long> {

    boolean existsByInvoiceNumber(
            String invoiceNumber
    );

    List<Invoice> findByPatientIdOrderByInvoiceDateDesc(
            Long patientId
    );

    List<Invoice> findByStatus(
            InvoiceStatus status
    );
}
