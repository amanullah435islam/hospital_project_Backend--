package com.hospital.appointment.dto;

import com.hospital.appointment.entity.AppointmentType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class AppointmentCreateRequest {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(
            message = "Appointment date cannot be in the past"
    )
    private LocalDate appointmentDate;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull(message = "Appointment type is required")
    private AppointmentType type;

    @Size(max = 500)
    private String reason;

    @Size(max = 1000)
    private String notes;

    /*
     * Later JWT authentication will provide this
     * automatically from SecurityContext.
     *
     * For now, MVP can accept createdBy user id.
     */
    private Long createdBy;
}
