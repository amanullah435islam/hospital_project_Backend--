package com.hospital.medical.service;

import com.hospital.appointment.entity.Appointment;
import com.hospital.appointment.entity.AppointmentStatus;
import com.hospital.appointment.repository.AppointmentRepository;
import com.hospital.common.exception.BadRequestException;
import com.hospital.common.exception.DuplicateResourceException;
import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.doctor.entity.Doctor;
import com.hospital.patient.entity.Patient;
import com.hospital.medical.dto.MedicalRecordCreateRequest;
import com.hospital.medical.dto.MedicalRecordResponse;
import com.hospital.medical.entity.MedicalRecord;
import com.hospital.medical.repository.MedicalRecordRepository;
import com.hospital.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicalRecordServiceImpl
        implements MedicalRecordService {

    private final MedicalRecordRepository
            medicalRecordRepository;

    private final AppointmentRepository
            appointmentRepository;


    @Override
    public MedicalRecordResponse createMedicalRecord(
            MedicalRecordCreateRequest request
    ) {

        /*
         * =====================================
         * 1. Find Appointment
         * =====================================
         */

        Appointment appointment =
                appointmentRepository.findById(
                        request.getAppointmentId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Appointment not found with id: "
                                        + request.getAppointmentId()
                        )
                );


        /*
         * =====================================
         * 2. Appointment must be completed
         * =====================================
         */

        if (appointment.getStatus()
                != AppointmentStatus.COMPLETED) {

            throw new BadRequestException(
                    "Medical record can only be created "
                            + "for a completed appointment"
            );
        }


        /*
         * =====================================
         * 3. One appointment = one record
         * =====================================
         */

        if (medicalRecordRepository
                .existsByAppointmentId(
                        appointment.getId()
                )) {

            throw new DuplicateResourceException(
                    "Medical record already exists "
                            + "for this appointment"
            );
        }


        /*
         * =====================================
         * 4. Create Medical Record
         * =====================================
         */

        MedicalRecord record =
                new MedicalRecord();

        record.setRecordCode(
                generateRecordCode()
        );

        record.setAppointment(
                appointment
        );

        record.setPatient(
                appointment.getPatient()
        );

        record.setDoctor(
                appointment.getDoctor()
        );

        record.setVisitDate(
                appointment.getAppointmentDate()
        );

        record.setChiefComplaint(
                request.getChiefComplaint()
        );

        record.setSymptoms(
                request.getSymptoms()
        );

        record.setDiagnosis(
                request.getDiagnosis()
        );

        record.setClinicalNotes(
                request.getClinicalNotes()
        );

        record.setTreatmentPlan(
                request.getTreatmentPlan()
        );

        record.setFollowUpDate(
                request.getFollowUpDate()
        );


        MedicalRecord savedRecord =
                medicalRecordRepository.save(
                        record
                );


        return mapToResponse(
                savedRecord
        );
    }


    @Override
    @Transactional(readOnly = true)
    public MedicalRecordResponse getMedicalRecordById(
            Long id
    ) {

        MedicalRecord record =
                medicalRecordRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Medical record not found "
                                                + "with id: "
                                                + id
                                )
                        );

        return mapToResponse(record);
    }


    @Override
    @Transactional(readOnly = true)
    public MedicalRecordResponse getByAppointmentId(
            Long appointmentId
    ) {

        MedicalRecord record =
                medicalRecordRepository
                        .findByAppointmentId(
                                appointmentId
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Medical record not found "
                                                + "for appointment: "
                                                + appointmentId
                                )
                        );

        return mapToResponse(record);
    }


    @Override
    @Transactional(readOnly = true)
    public List<MedicalRecordResponse>
    getPatientMedicalHistory(
            Long patientId
    ) {

        return medicalRecordRepository
                .findByPatientIdOrderByVisitDateDesc(
                        patientId
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<MedicalRecordResponse>
    getDoctorMedicalRecords(
            Long doctorId
    ) {

        return medicalRecordRepository
                .findByDoctorIdOrderByVisitDateDesc(
                        doctorId
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    private String generateRecordCode() {

        long nextId =
                medicalRecordRepository.count() + 1;

        String code;

        do {

            code = String.format(
                    "MR-%06d",
                    nextId
            );

            nextId++;

        } while (
                medicalRecordRepository
                        .existsByRecordCode(code)
        );

        return code;
    }


    private MedicalRecordResponse mapToResponse(
            MedicalRecord record
    ) {

        Patient patient =
                record.getPatient();

        Doctor doctor =
                record.getDoctor();

        User doctorUser =
                doctor.getUser();

        String doctorName =
                doctorUser.getFirstName()
                        + " "
                        + doctorUser.getLastName();

        String patientName =
                patient.getFirstName()
                        + " "
                        + (
                        patient.getLastName() != null
                                ? patient.getLastName()
                                : ""
                );

        return MedicalRecordResponse.builder()
                .id(record.getId())
                .recordCode(
                        record.getRecordCode()
                )
                .appointmentId(
                        record.getAppointment().getId()
                )
                .appointmentCode(
                        record.getAppointment()
                                .getAppointmentCode()
                )
                .patientId(
                        patient.getId()
                )
                .patientCode(
                        patient.getPatientCode()
                )
                .patientName(
                        patientName.trim()
                )
                .doctorId(
                        doctor.getId()
                )
                .doctorCode(
                        doctor.getDoctorCode()
                )
                .doctorName(
                        doctorName.trim()
                )
                .departmentName(
                        doctor.getDepartment()
                                .getName()
                )
                .visitDate(
                        record.getVisitDate()
                )
                .chiefComplaint(
                        record.getChiefComplaint()
                )
                .symptoms(
                        record.getSymptoms()
                )
                .diagnosis(
                        record.getDiagnosis()
                )
                .clinicalNotes(
                        record.getClinicalNotes()
                )
                .treatmentPlan(
                        record.getTreatmentPlan()
                )
                .followUpDate(
                        record.getFollowUpDate()
                )
                .build();
    }
}
