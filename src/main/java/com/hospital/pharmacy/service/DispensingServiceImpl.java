package com.hospital.pharmacy.service;

import com.hospital.common.exception.BadRequestException;
import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.pharmacy.dto.*;
import com.hospital.prescription.entity.Prescription;
import com.hospital.prescription.entity.PrescriptionItem;
import com.hospital.prescription.entity.PrescriptionStatus;
import com.hospital.prescription.repository.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DispensingServiceImpl
        implements DispensingService {

    private final PrescriptionRepository
            prescriptionRepository;

    private final StockService stockService;


    @Override
    @Transactional
    public DispensePrescriptionResponse
    dispensePrescription(
            DispensePrescriptionRequest request
    ) {

        // ======================================
        // FIND PRESCRIPTION
        // ======================================

        Prescription prescription =
                prescriptionRepository
                        .findById(
                                request.getPrescriptionId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Prescription not found with id: "
                                                + request.getPrescriptionId()
                                )
                        );


        // ======================================
        // STATUS CHECK
        // ======================================

        if (
                prescription.getStatus()
                        == PrescriptionStatus.DISPENSED
        ) {

            throw new BadRequestException(
                    "Prescription has already been dispensed"
            );
        }


        if (
                prescription.getStatus()
                        == PrescriptionStatus.CANCELLED
        ) {

            throw new BadRequestException(
                    "Cancelled prescription cannot be dispensed"
            );
        }


        // ======================================
        // CHECK ITEMS
        // ======================================

        if (
                prescription.getItems() == null
                        ||
                        prescription.getItems().isEmpty()
        ) {

            throw new BadRequestException(
                    "Prescription has no medicine items"
            );
        }


        // ======================================
        // DISPENSE
        // ======================================

        List<DispensedMedicineResponse>
                dispensedMedicines =
                new ArrayList<>();


        for (
                PrescriptionItem item
                : prescription.getItems()
        ) {

            // ----------------------------------
            // VALIDATE MEDICINE
            // ----------------------------------

            if (item.getMedicine() == null) {

                throw new BadRequestException(
                        "Prescription item has no medicine"
                );
            }


            // ----------------------------------
            // VALIDATE QUANTITY
            // ----------------------------------

            if (
                    item.getQuantity() == null
                            ||
                            item.getQuantity() <= 0
            ) {

                throw new BadRequestException(
                        "Invalid quantity for medicine: "
                                + item.getMedicine().getName()
                );
            }


            // ----------------------------------
            // CREATE STOCK REQUEST
            // ----------------------------------

            StockIssueRequest stockRequest =
                    new StockIssueRequest();

            stockRequest.setMedicineId(
                    item.getMedicine().getId()
            );

            stockRequest.setQuantity(
                    item.getQuantity()
            );

            stockRequest.setReferenceType(
                    "PRESCRIPTION"
            );

            stockRequest.setReferenceId(
                    prescription.getId()
            );

            stockRequest.setReason(
                    "Dispensed for prescription #"
                            + prescription.getId()
            );


            // ----------------------------------
            // ISSUE STOCK
            // ----------------------------------

            StockIssueResponse stockResponse =
                    stockService.issueStock(
                            stockRequest
                    );


            // ----------------------------------
            // ADD RESPONSE
            // ----------------------------------

            dispensedMedicines.add(
                    DispensedMedicineResponse
                            .builder()
                            .medicineId(
                                    stockResponse
                                            .getMedicineId()
                            )
                            .medicineName(
                                    stockResponse
                                            .getMedicineName()
                            )
                            .requestedQuantity(
                                    stockResponse
                                            .getRequestedQuantity()
                            )
                            .issuedQuantity(
                                    stockResponse
                                            .getIssuedQuantity()
                            )
                            .batches(
                                    stockResponse
                                            .getBatches()
                            )
                            .build()
            );
        }


        // ======================================
        // MARK PRESCRIPTION AS DISPENSED
        // ======================================

        prescription.setStatus(
                PrescriptionStatus.DISPENSED
        );

        prescriptionRepository.save(
                prescription
        );


        // ======================================
        // RESPONSE
        // ======================================

        return DispensePrescriptionResponse
                .builder()
                .prescriptionId(
                        prescription.getId()
                )
                .status(
                        PrescriptionStatus.DISPENSED.name()
                )
                .medicines(
                        dispensedMedicines
                )
                .build();
    }
}