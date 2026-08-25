package com.hospital.appointment.service;

import com.hospital.appointment.dto.AppointmentCreateRequest;
import com.hospital.appointment.dto.AppointmentResponse;
import com.hospital.appointment.entity.Appointment;
import com.hospital.appointment.entity.AppointmentStatus;
import com.hospital.appointment.repository.AppointmentRepository;
import com.hospital.common.exception.BadRequestException;
import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.doctor.entity.Doctor;
import com.hospital.doctor.entity.DoctorStatus;
import com.hospital.doctor.repository.DoctorRepository;
import com.hospital.patient.entity.Patient;
import com.hospital.patient.entity.PatientStatus;
import com.hospital.patient.repository.PatientRepository;
import com.hospital.user.entity.User;
import com.hospital.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImpl
        implements AppointmentService {

    private final AppointmentRepository
            appointmentRepository;

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;

    private final UserRepository userRepository;


    @Override
    public AppointmentResponse createAppointment(
            AppointmentCreateRequest request
    ) {

        /*
         * =====================================
         * 1. Validate Time
         * =====================================
         */

        if (!request.getStartTime()
                .isBefore(request.getEndTime())) {

            throw new BadRequestException(
                    "Start time must be before end time"
            );
        }


        /*
         * =====================================
         * 2. Validate Patient
         * =====================================
         */

        Patient patient =
                patientRepository.findById(
                        request.getPatientId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found with id: "
                                        + request.getPatientId()
                        )
                );


        if (patient.getStatus()
                != PatientStatus.ACTIVE) {

            throw new BadRequestException(
                    "Patient is not active"
            );
        }


        /*
         * =====================================
         * 3. Validate Doctor
         * =====================================
         */

        Doctor doctor =
                doctorRepository.findById(
                        request.getDoctorId()
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found with id: "
                                        + request.getDoctorId()
                        )
                );


        if (doctor.getStatus()
                != DoctorStatus.ACTIVE) {

            throw new BadRequestException(
                    "Doctor is not active"
            );
        }


        /*
         * =====================================
         * 4. Doctor Time Conflict
         * =====================================
         */

        boolean doctorConflict =
                hasDoctorConflict(
                        doctor.getId(),
                        request.getAppointmentDate(),
                        request.getStartTime(),
                        request.getEndTime()
                );

        if (doctorConflict) {

            throw new BadRequestException(
                    "Doctor already has an appointment "
                            + "during this time"
            );
        }


        /*
         * =====================================
         * 5. Patient Time Conflict
         * =====================================
         */

        boolean patientConflict =
                hasPatientConflict(
                        patient.getId(),
                        request.getAppointmentDate(),
                        request.getStartTime(),
                        request.getEndTime()
                );

        if (patientConflict) {

            throw new BadRequestException(
                    "Patient already has an appointment "
                            + "during this time"
            );
        }


        /*
         * =====================================
         * 6. Optional Created By
         * =====================================
         */

        User createdBy = null;

        if (request.getCreatedBy() != null) {

            createdBy =
                    userRepository.findById(
                            request.getCreatedBy()
                    ).orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "User not found with id: "
                                            + request.getCreatedBy()
                            )
                    );
        }


        /*
         * =====================================
         * 7. Create Appointment
         * =====================================
         */

        Appointment appointment =
                new Appointment();

        appointment.setAppointmentCode(
                generateAppointmentCode()
        );

        appointment.setPatient(patient);

        appointment.setDoctor(doctor);

        appointment.setAppointmentDate(
                request.getAppointmentDate()
        );

        appointment.setStartTime(
                request.getStartTime()
        );

        appointment.setEndTime(
                request.getEndTime()
        );

        appointment.setType(
                request.getType()
        );

        appointment.setStatus(
                AppointmentStatus.SCHEDULED
        );

        appointment.setReason(
                request.getReason()
        );

        appointment.setNotes(
                request.getNotes()
        );

        appointment.setCreatedBy(
                createdBy
        );


        Appointment savedAppointment =
                appointmentRepository.save(
                        appointment
                );


        return mapToResponse(
                savedAppointment
        );
    }


    @Override
    @Transactional(readOnly = true)
    public AppointmentResponse getAppointmentById(
            Long id
    ) {

        Appointment appointment =
                appointmentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Appointment not found with id: "
                                                + id
                                )
                        );

        return mapToResponse(appointment);
    }


    @Override
    @Transactional(readOnly = true)
    public List<AppointmentResponse>
    getAllAppointments() {

        return appointmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<AppointmentResponse>
    getDoctorAppointments(
            Long doctorId,
            LocalDate date
    ) {

        if (!doctorRepository.existsById(
                doctorId
        )) {

            throw new ResourceNotFoundException(
                    "Doctor not found with id: "
                            + doctorId
            );
        }

        return appointmentRepository
                .findByDoctorIdAndAppointmentDate(
                        doctorId,
                        date
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<AppointmentResponse>
    getPatientAppointments(
            Long patientId,
            LocalDate date
    ) {

        if (!patientRepository.existsById(
                patientId
        )) {

            throw new ResourceNotFoundException(
                    "Patient not found with id: "
                            + patientId
            );
        }

        return appointmentRepository
                .findByPatientIdAndAppointmentDate(
                        patientId,
                        date
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    /*
     * =========================================
     * Doctor Conflict
     * =========================================
     */

    private boolean hasDoctorConflict(
            Long doctorId,
            LocalDate date,
            LocalTime newStart,
            LocalTime newEnd
    ) {

        List<Appointment> appointments =
                appointmentRepository
                        .findByDoctorIdAndAppointmentDateAndStatusNot(
                                doctorId,
                                date,
                                AppointmentStatus.CANCELLED
                        );

        return appointments.stream()
                .anyMatch(existing ->
                        isTimeOverlap(
                                newStart,
                                newEnd,
                                existing.getStartTime(),
                                existing.getEndTime()
                        )
                );
    }


    /*
     * =========================================
     * Patient Conflict
     * =========================================
     */

    private boolean hasPatientConflict(
            Long patientId,
            LocalDate date,
            LocalTime newStart,
            LocalTime newEnd
    ) {

        List<Appointment> appointments =
                appointmentRepository
                        .findByPatientIdAndAppointmentDate(
                                patientId,
                                date
                        );

        return appointments.stream()
                .filter(appointment ->
                        appointment.getStatus()
                                != AppointmentStatus.CANCELLED
                )
                .anyMatch(existing ->
                        isTimeOverlap(
                                newStart,
                                newEnd,
                                existing.getStartTime(),
                                existing.getEndTime()
                        )
                );
    }


    /*
     * =========================================
     * Time Overlap Algorithm
     * =========================================
     */

    private boolean isTimeOverlap(
            LocalTime newStart,
            LocalTime newEnd,
            LocalTime existingStart,
            LocalTime existingEnd
    ) {

        return newStart.isBefore(existingEnd)
                && newEnd.isAfter(existingStart);
    }


    /*
     * =========================================
     * Appointment Code
     * =========================================
     */

    private String generateAppointmentCode() {

        long nextId =
                appointmentRepository.count() + 1;

        String code;

        do {

            code = String.format(
                    "APT-%06d",
                    nextId
            );

            nextId++;

        } while (
                appointmentRepository
                        .existsByAppointmentCode(code)
        );

        return code;
    }


    /*
     * =========================================
     * Entity → Response
     * =========================================
     */

    private AppointmentResponse mapToResponse(
            Appointment appointment
    ) {

        Patient patient =
                appointment.getPatient();

        Doctor doctor =
                appointment.getDoctor();

        User doctorUser =
                doctor.getUser();

        String doctorName =
                doctorUser.getFirstName()
                        + " "
                        + doctorUser.getLastName();

        return AppointmentResponse.builder()
                .id(appointment.getId())
                .appointmentCode(
                        appointment.getAppointmentCode()
                )
                .patientId(
                        patient.getId()
                )
                .patientCode(
                        patient.getPatientCode()
                )
                .patientName(
                        buildFullName(
                                patient.getFirstName(),
                                patient.getLastName()
                        )
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
                .appointmentDate(
                        appointment.getAppointmentDate()
                )
                .startTime(
                        appointment.getStartTime()
                )
                .endTime(
                        appointment.getEndTime()
                )
                .type(
                        appointment.getType()
                )
                .status(
                        appointment.getStatus()
                )
                .reason(
                        appointment.getReason()
                )
                .notes(
                        appointment.getNotes()
                )
                .createdBy(
                        appointment.getCreatedBy() != null
                                ? appointment
                                  .getCreatedBy()
                                  .getId()
                                : null
                )
                .build();
    }


    private String buildFullName(
            String firstName,
            String lastName
    ) {

        return (
                firstName
                        + " "
                        + (
                        lastName != null
                                ? lastName
                                : ""
                )
        ).trim();
    }


    /*
     * =====================================
     * 7. Update Status on Appointment
     * =====================================
     */

    @Override
    public void updateAppointmentStatus(
            Long appointmentId,
            AppointmentStatus newStatus
    ) {

        Appointment appointment =
                appointmentRepository.findById(
                        appointmentId
                ).orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Appointment not found with id: "
                                        + appointmentId
                        )
                );

        AppointmentStatus currentStatus =
                appointment.getStatus();


        /*
         * =========================================
         * Same status
         * =========================================
         */

        if (currentStatus == newStatus) {

            throw new BadRequestException(
                    "Appointment is already "
                            + newStatus
            );
        }


        /*
         * =========================================
         * CANCELLED
         * =========================================
         */

        if (currentStatus
                == AppointmentStatus.CANCELLED) {

            throw new BadRequestException(
                    "Cancelled appointment cannot be modified"
            );
        }


        /*
         * =========================================
         * COMPLETED
         * =========================================
         */

        if (currentStatus
                == AppointmentStatus.COMPLETED) {

            throw new BadRequestException(
                    "Completed appointment cannot be modified"
            );
        }


        /*
         * =========================================
         * NO_SHOW
         * =========================================
         */

        if (currentStatus
                == AppointmentStatus.NO_SHOW) {

            throw new BadRequestException(
                    "No-show appointment cannot be modified"
            );
        }


        /*
         * =========================================
         * Valid State Transition
         * =========================================
         */

        boolean validTransition =
                isValidTransition(
                        currentStatus,
                        newStatus
                );

        if (!validTransition) {

            throw new BadRequestException(
                    "Invalid appointment status transition: "
                            + currentStatus
                            + " → "
                            + newStatus
            );
        }


        appointment.setStatus(
                newStatus
        );
    }


    /*
     * =====================================
     * 7. Extra Sub code
     * =====================================
     */

    private boolean isValidTransition(
            AppointmentStatus current,
            AppointmentStatus next
    ) {

        return switch (current) {

            case SCHEDULED ->
                    next == AppointmentStatus.CONFIRMED
                            || next == AppointmentStatus.CANCELLED
                            || next == AppointmentStatus.NO_SHOW;

            case CONFIRMED ->
                    next == AppointmentStatus.IN_PROGRESS
                            || next == AppointmentStatus.CANCELLED
                            || next == AppointmentStatus.NO_SHOW;

            case IN_PROGRESS ->
                    next == AppointmentStatus.COMPLETED;

            case COMPLETED,
                 CANCELLED,
                 NO_SHOW ->
                    false;
        };
    }
}
