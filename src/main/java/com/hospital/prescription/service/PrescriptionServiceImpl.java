package com.hospital.prescription.service;

import com.hospital.common.exception.BadRequestException;
import com.hospital.common.exception.DuplicateResourceException;
import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.doctor.entity.Doctor;
import com.hospital.medicine.entity.Medicine;
import com.hospital.medicine.entity.MedicineStatus;
import com.hospital.medicine.repository.MedicineRepository;
import com.hospital.medical.entity.MedicalRecord;
import com.hospital.medical.repository.MedicalRecordRepository;
import com.hospital.patient.entity.Patient;
import com.hospital.prescription.dto.PrescriptionCreateRequest;
import com.hospital.prescription.dto.PrescriptionItemRequest;
import com.hospital.prescription.dto.PrescriptionItemResponse;
import com.hospital.prescription.dto.PrescriptionResponse;
import com.hospital.prescription.entity.Prescription;
import com.hospital.prescription.entity.PrescriptionItem;
import com.hospital.prescription.entity.PrescriptionStatus;
import com.hospital.prescription.repository.PrescriptionRepository;
import com.hospital.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PrescriptionServiceImpl
        implements PrescriptionService {

    private final PrescriptionRepository
            prescriptionRepository;

    private final MedicalRecordRepository
            medicalRecordRepository;

    private final MedicineRepository
            medicineRepository;


    @Override
    public PrescriptionResponse createPrescription(
            PrescriptionCreateRequest request
    ) {

        /*
         * =====================================
         * 1. Find Medical Record
         * =====================================
         */

        MedicalRecord medicalRecord =
                medicalRecordRepository.findById(
                        request.getMedicalRecordId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Medical record not found with id: "
                                        + request.getMedicalRecordId()
                        )
                );


        /*
         * =====================================
         * 2. One MedicalRecord → One Prescription
         * =====================================
         */

        if (prescriptionRepository
                .existsByMedicalRecordId(
                        medicalRecord.getId()
                )) {

            throw new DuplicateResourceException(
                    "Prescription already exists "
                            + "for this medical record"
            );
        }


        /*
         * =====================================
         * 3. Validate Medicine List
         * =====================================
         */

        if (request.getItems() == null
                || request.getItems().isEmpty()) {

            throw new BadRequestException(
                    "Prescription must contain "
                            + "at least one medicine"
            );
        }


        /*
         * =====================================
         * 4. Create Prescription
         * =====================================
         */

        Prescription prescription =
                new Prescription();

        prescription.setPrescriptionCode(
                generatePrescriptionCode()
        );

        prescription.setMedicalRecord(
                medicalRecord
        );

        prescription.setPatient(
                medicalRecord.getPatient()
        );

        prescription.setDoctor(
                medicalRecord.getDoctor()
        );

        prescription.setPrescribedDate(
                LocalDate.now()
        );

        prescription.setNotes(
                request.getNotes()
        );


        /*
         * =====================================
         * 5. Create Prescription Items
         * =====================================
         */

        for (
                PrescriptionItemRequest itemRequest
                : request.getItems()
        ) {

            Medicine medicine =
                    medicineRepository.findById(
                            itemRequest.getMedicineId()
                    ).orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Medicine not found with id: "
                                            + itemRequest
                                            .getMedicineId()
                            )
                    );


            /*
             * Medicine must be active
             */

            if (medicine.getStatus()
                    != MedicineStatus.ACTIVE) {

                throw new BadRequestException(
                        "Medicine is inactive: "
                                + medicine.getName()
                );
            }


            PrescriptionItem item =
                    new PrescriptionItem();

            item.setMedicine(
                    medicine
            );

            item.setDosage(
                    itemRequest.getDosage()
            );

            item.setFrequency(
                    itemRequest.getFrequency()
            );

            item.setDuration(
                    itemRequest.getDuration()
            );

            item.setRoute(
                    itemRequest.getRoute()
            );

            item.setInstructions(
                    itemRequest.getInstructions()
            );


            /*
             * Important:
             *
             * This automatically sets:
             *
             * item.prescription = prescription
             */

            prescription.addItem(item);
        }


        /*
         * =====================================
         * 6. Save
         * =====================================
         */

        prescription.setStatus(
                PrescriptionStatus.ACTIVE
        );

        Prescription saved =
                prescriptionRepository.save(
                        prescription
                );


        return mapToResponse(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public PrescriptionResponse getPrescriptionById(
            Long id
    ) {

        Prescription prescription =
                prescriptionRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Prescription not found "
                                                + "with id: "
                                                + id
                                )
                        );

        return mapToResponse(prescription);
    }


    @Override
    @Transactional(readOnly = true)
    public PrescriptionResponse
    getByMedicalRecordId(
            Long medicalRecordId
    ) {

        Prescription prescription =
                prescriptionRepository
                        .findByMedicalRecordId(
                                medicalRecordId
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Prescription not found "
                                                + "for medical record: "
                                                + medicalRecordId
                                )
                        );

        return mapToResponse(prescription);
    }


    private String generatePrescriptionCode() {

        long nextId =
                prescriptionRepository.count() + 1;

        String code;

        do {

            code = String.format(
                    "RX-%06d",
                    nextId
            );

            nextId++;

        } while (
                prescriptionRepository
                        .existsByPrescriptionCode(code)
        );

        return code;
    }


    private PrescriptionResponse mapToResponse(
            Prescription prescription
    ) {

        Patient patient =
                prescription.getPatient();

        Doctor doctor =
                prescription.getDoctor();

        User doctorUser =
                doctor.getUser();

        String patientName =
                (
                        patient.getFirstName()
                                + " "
                                + (
                                patient.getLastName() != null
                                        ? patient.getLastName()
                                        : ""
                        )
                ).trim();

        String doctorName =
                (
                        doctorUser.getFirstName()
                                + " "
                                + doctorUser.getLastName()
                ).trim();


        List<PrescriptionItemResponse>
                itemResponses =
                prescription.getItems()
                        .stream()
                        .map(item ->
                                PrescriptionItemResponse
                                        .builder()
                                        .id(item.getId())
                                        .medicineId(
                                                item.getMedicine().getId()
                                        )
                                        .medicineCode(
                                                item.getMedicine()
                                                        .getMedicineCode()
                                        )
                                        .medicineName(
                                                item.getMedicine()
                                                        .getName()
                                        )
                                        .genericName(
                                                item.getMedicine()
                                                        .getGenericName()
                                        )
                                        .strength(
                                                item.getMedicine()
                                                        .getStrength()
                                        )
                                        .form(
                                                item.getMedicine()
                                                        .getForm()
                                        )
                                        .dosage(
                                                item.getDosage()
                                        )
                                        .frequency(
                                                item.getFrequency()
                                        )
                                        .duration(
                                                item.getDuration()
                                        )
                                        .route(
                                                item.getRoute()
                                        )
                                        .instructions(
                                                item.getInstructions()
                                        )
                                        .build()
                        )
                        .toList();


        return PrescriptionResponse
                .builder()
                .id(prescription.getId())
                .prescriptionCode(
                        prescription.getPrescriptionCode()
                )
                .medicalRecordId(
                        prescription.getMedicalRecord()
                                .getId()
                )
                .patientId(
                        patient.getId()
                )
                .patientCode(
                        patient.getPatientCode()
                )
                .patientName(
                        patientName
                )
                .doctorId(
                        doctor.getId()
                )
                .doctorCode(
                        doctor.getDoctorCode()
                )
                .doctorName(
                        doctorName
                )
                .prescribedDate(
                        prescription.getPrescribedDate()
                )
                .notes(
                        prescription.getNotes()
                )
                .items(
                        itemResponses
                )
                .build();
    }
}
