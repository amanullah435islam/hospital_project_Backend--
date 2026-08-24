package com.hospital.appointment.dto;

import com.hospital.appointment.entity.AppointmentStatus;
import com.hospital.appointment.entity.AppointmentType;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class AppointmentResponse {

    private Long id;

    private String appointmentCode;

    private Long patientId;

    private String patientCode;

    private String patientName;

    private Long doctorId;

    private String doctorCode;

    private String doctorName;

    private String departmentName;

    private LocalDate appointmentDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private AppointmentType type;

    private AppointmentStatus status;

    private String reason;

    private String notes;

    private Long createdBy;
}
