package com.hospital.billing.service;

import com.hospital.billing.dto.InvoiceCreateRequest;
import com.hospital.billing.dto.InvoiceItemRequest;
import com.hospital.billing.dto.InvoiceItemResponse;
import com.hospital.billing.dto.InvoiceResponse;
import com.hospital.billing.entity.Invoice;
import com.hospital.billing.entity.InvoiceItem;
import com.hospital.billing.entity.InvoiceStatus;
import com.hospital.billing.repository.InvoiceRepository;
import com.hospital.billing.repository.PaymentRepository;
import com.hospital.common.exception.BadRequestException;
import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.patient.entity.Patient;
import com.hospital.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InvoiceServiceImpl
        implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final PaymentRepository paymentRepository;

    private final PatientRepository patientRepository;


    @Override
    public InvoiceResponse createInvoice(
            InvoiceCreateRequest request
    ) {

        Patient patient =
                patientRepository.findById(
                        request.getPatientId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found with id: "
                                        + request.getPatientId()
                        )
                );


        Invoice invoice =
                new Invoice();

        invoice.setInvoiceNumber(
                generateInvoiceNumber()
        );

        invoice.setPatient(
                patient
        );

        invoice.setInvoiceDate(
                LocalDate.now()
        );

        invoice.setStatus(
                InvoiceStatus.UNPAID
        );

        invoice.setDiscount(
                request.getDiscount() != null
                        ? request.getDiscount()
                        : BigDecimal.ZERO
        );

        invoice.setTax(
                request.getTax() != null
                        ? request.getTax()
                        : BigDecimal.ZERO
        );

        invoice.setNotes(
                request.getNotes()
        );


        for (
                InvoiceItemRequest itemRequest
                : request.getItems()
        ) {

            InvoiceItem item =
                    new InvoiceItem();

            item.setDescription(
                    itemRequest.getDescription()
            );

            item.setUnitPrice(
                    itemRequest.getUnitPrice()
            );

            item.setQuantity(
                    itemRequest.getQuantity()
            );

            invoice.addItem(item);
        }


        Invoice saved =
                invoiceRepository.save(
                        invoice
                );


        return mapToResponse(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public InvoiceResponse getInvoiceById(
            Long id
    ) {

        Invoice invoice =
                findInvoice(id);

        return mapToResponse(invoice);
    }


    @Override
    @Transactional(readOnly = true)
    public List<InvoiceResponse>
    getPatientInvoices(
            Long patientId
    ) {

        if (!patientRepository.existsById(patientId)) {

            throw new ResourceNotFoundException(
                    "Patient not found with id: "
                            + patientId
            );
        }

        return invoiceRepository
                .findByPatientIdOrderByInvoiceDateDesc(
                        patientId
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    public InvoiceResponse cancelInvoice(
            Long id
    ) {

        Invoice invoice =
                findInvoice(id);


        if (invoice.getStatus()
                == InvoiceStatus.PAID) {

            throw new BadRequestException(
                    "Paid invoice cannot be cancelled"
            );
        }


        if (invoice.getStatus()
                == InvoiceStatus.CANCELLED) {

            throw new BadRequestException(
                    "Invoice is already cancelled"
            );
        }


        BigDecimal paidAmount =
                paymentRepository
                        .findTotalPaidByInvoiceId(
                                invoice.getId()
                        );


        if (paidAmount.compareTo(
                BigDecimal.ZERO
        ) > 0) {

            throw new BadRequestException(
                    "Invoice with payment cannot "
                            + "be cancelled directly"
            );
        }


        invoice.setStatus(
                InvoiceStatus.CANCELLED
        );


        return mapToResponse(invoice);
    }


    private Invoice findInvoice(
            Long id
    ) {

        return invoiceRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Invoice not found with id: "
                                        + id
                        )
                );
    }


    private String generateInvoiceNumber() {

        long nextId =
                invoiceRepository.count() + 1;

        String number;

        do {

            number = String.format(
                    "INV-%06d",
                    nextId
            );

            nextId++;

        } while (
                invoiceRepository
                        .existsByInvoiceNumber(number)
        );

        return number;
    }


    private InvoiceResponse mapToResponse(
            Invoice invoice
    ) {

        BigDecimal subtotal =
                invoice.getItems()
                        .stream()
                        .map(InvoiceItem::getTotal)
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


        BigDecimal paidAmount =
                paymentRepository
                        .findTotalPaidByInvoiceId(
                                invoice.getId()
                        );


        BigDecimal dueAmount =
                grandTotal.subtract(
                        paidAmount
                );


        List<InvoiceItemResponse>
                itemResponses =
                invoice.getItems()
                        .stream()
                        .map(item ->
                                InvoiceItemResponse
                                        .builder()
                                        .id(
                                                item.getId()
                                        )
                                        .description(
                                                item.getDescription()
                                        )
                                        .unitPrice(
                                                item.getUnitPrice()
                                        )
                                        .quantity(
                                                item.getQuantity()
                                        )
                                        .total(
                                                item.getTotal()
                                        )
                                        .build()
                        )
                        .toList();


        String patientName =
                (
                        invoice.getPatient()
                                .getFirstName()
                                + " "
                                + (
                                invoice.getPatient()
                                        .getLastName() != null
                                        ? invoice.getPatient()
                                          .getLastName()
                                        : ""
                        )
                ).trim();


        return InvoiceResponse
                .builder()
                .id(invoice.getId())
                .invoiceNumber(
                        invoice.getInvoiceNumber()
                )
                .patientId(
                        invoice.getPatient().getId()
                )
                .patientCode(
                        invoice.getPatient().getPatientCode()
                )
                .patientName(
                        patientName
                )
                .invoiceDate(
                        invoice.getInvoiceDate()
                )
                .status(
                        invoice.getStatus()
                )
                .subtotal(
                        subtotal
                )
                .discount(
                        discount
                )
                .tax(
                        tax
                )
                .grandTotal(
                        grandTotal
                )
                .paidAmount(
                        paidAmount
                )
                .dueAmount(
                        dueAmount
                )
                .notes(
                        invoice.getNotes()
                )
                .items(
                        itemResponses
                )
                .build();
    }
}
