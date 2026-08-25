package com.hospital.appointment.service;

import com.hospital.appointment.dto.AppointmentCreateRequest;
import com.hospital.appointment.dto.AppointmentResponse;
import com.hospital.appointment.entity.AppointmentStatus;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    AppointmentResponse createAppointment(
            AppointmentCreateRequest request
    );

    AppointmentResponse getAppointmentById(
            Long id
    );

    List<AppointmentResponse> getAllAppointments();

    List<AppointmentResponse> getDoctorAppointments(
            Long doctorId,
            LocalDate date
    );

    List<AppointmentResponse> getPatientAppointments(
            Long patientId,
            LocalDate date
    );

    void updateAppointmentStatus(
            Long appointmentId,
            AppointmentStatus newStatus
    );

}
