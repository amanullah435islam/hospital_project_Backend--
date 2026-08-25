package com.hospital.billing.service;

import com.hospital.billing.dto.PaymentCreateRequest;
import com.hospital.billing.dto.PaymentResponse;
import com.hospital.billing.entity.Invoice;
import com.hospital.billing.entity.InvoiceStatus;
import com.hospital.billing.entity.Payment;
import com.hospital.billing.repository.InvoiceRepository;
import com.hospital.billing.repository.PaymentRepository;
import com.hospital.common.exception.BadRequestException;
import com.hospital.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl
        implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final InvoiceRepository invoiceRepository;


    @Override
    public PaymentResponse createPayment(
            PaymentCreateRequest request
    ) {

        Invoice invoice =
                invoiceRepository.findById(
                        request.getInvoiceId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Invoice not found with id: "
                                        + request.getInvoiceId()
                        )
                );


        if (invoice.getStatus()
                == InvoiceStatus.CANCELLED) {

            throw new BadRequestException(
                    "Cannot make payment for "
                            + "cancelled invoice"
            );
        }


        if (invoice.getStatus()
                == InvoiceStatus.PAID) {

            throw new BadRequestException(
                    "Invoice is already fully paid"
            );
        }


        BigDecimal subtotal =
                invoice.getItems()
                        .stream()
                        .map(item ->
                                item.getTotal()
                        )
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );


        BigDecimal discount =
                invoice.getDiscount() != null
                        ? invoice.getDiscount()
                        : BigDecimal.ZERO;

        BigDecimal tax =
                invoice.getTax() != null
                        ? invoice.getTax()
                        : BigDecimal.ZERO;


        BigDecimal grandTotal =
                subtotal
                        .subtract(discount)
                        .add(tax);


        BigDecimal alreadyPaid =
                paymentRepository
                        .findTotalPaidByInvoiceId(
                                invoice.getId()
                        );


        BigDecimal due =
                grandTotal.subtract(
                        alreadyPaid
                );


        if (request.getAmount()
                .compareTo(due) > 0) {

            throw new BadRequestException(
                    "Payment amount cannot exceed "
                            + "due amount: "
                            + due
            );
        }


        Payment payment =
                new Payment();

        payment.setPaymentReference(
                generatePaymentReference()
        );

        payment.setInvoice(
                invoice
        );

        payment.setAmount(
                request.getAmount()
        );

        payment.setPaymentMethod(
                request.getPaymentMethod()
        );

        payment.setPaymentDate(
                LocalDateTime.now()
        );

        payment.setNotes(
                request.getNotes()
        );


        Payment saved =
                paymentRepository.save(
                        payment
                );


        BigDecimal newPaid =
                alreadyPaid.add(
                        request.getAmount()
                );


        if (newPaid.compareTo(
                grandTotal
        ) == 0) {

            invoice.setStatus(
                    InvoiceStatus.PAID
            );

        } else {

            invoice.setStatus(
                    InvoiceStatus.PARTIALLY_PAID
            );
        }


        return mapToResponse(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponse>
    getInvoicePayments(
            Long invoiceId
    ) {

        if (!invoiceRepository.existsById(
                invoiceId
        )) {

            throw new ResourceNotFoundException(
                    "Invoice not found with id: "
                            + invoiceId
            );
        }


        return paymentRepository
                .findByInvoiceIdOrderByPaymentDateDesc(
                        invoiceId
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    private String generatePaymentReference() {

        long nextId =
                paymentRepository.count() + 1;

        String reference;

        do {

            reference = String.format(
                    "PAY-%08d",
                    nextId
            );

            nextId++;

        } while (
                paymentRepository
                        .existsByPaymentReference(
                                reference
                        )
        );

        return reference;
    }


    private PaymentResponse mapToResponse(
            Payment payment
    ) {

        return PaymentResponse
                .builder()
                .id(
                        payment.getId()
                )
                .paymentReference(
                        payment.getPaymentReference()
                )
                .invoiceId(
                        payment.getInvoice().getId()
                )
                .invoiceNumber(
                        payment.getInvoice()
                                .getInvoiceNumber()
                )
                .amount(
                        payment.getAmount()
                )
                .paymentMethod(
                        payment.getPaymentMethod()
                )
                .paymentDate(
                        payment.getPaymentDate()
                )
                .notes(
                        payment.getNotes()
                )
                .build();
    }
}
