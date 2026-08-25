package com.hospital.billing.service;

import com.hospital.billing.dto.PaymentCreateRequest;
import com.hospital.billing.dto.PaymentResponse;
import java.util.List;

public interface PaymentService {

    PaymentResponse createPayment(
            PaymentCreateRequest request
    );

    List<PaymentResponse> getInvoicePayments(
            Long invoiceId
    );
}
