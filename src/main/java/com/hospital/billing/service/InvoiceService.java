package com.hospital.billing.service;

import com.hospital.billing.dto.InvoiceCreateRequest;
import com.hospital.billing.dto.InvoiceResponse;
import java.util.List;

public interface InvoiceService {

    InvoiceResponse createInvoice(
            InvoiceCreateRequest request
    );

    InvoiceResponse getInvoiceById(
            Long id
    );

    List<InvoiceResponse> getPatientInvoices(
            Long patientId
    );

    InvoiceResponse cancelInvoice(
            Long id
    );
}
