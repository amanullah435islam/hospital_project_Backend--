package com.hospital.appointment.controller;

import com.hospital.appointment.dto.AppointmentCreateRequest;
import com.hospital.appointment.dto.AppointmentResponse;
import com.hospital.appointment.service.AppointmentService;
import com.hospital.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;


    @PostMapping
    public ResponseEntity<
            ApiResponse<AppointmentResponse>
            > createAppointment(

            @Valid
            @RequestBody
            AppointmentCreateRequest request
    ) {

        AppointmentResponse response =
                appointmentService
                        .createAppointment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Appointment created successfully",
                                response
                        )
                );
    }


    @GetMapping("/{id}")
    public ResponseEntity<
            ApiResponse<AppointmentResponse>
            > getAppointment(
            @PathVariable Long id
    ) {

        AppointmentResponse response =
                appointmentService
                        .getAppointmentById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Appointment retrieved successfully",
                        response
                )
        );
    }


    @GetMapping
    public ResponseEntity<
            ApiResponse<List<AppointmentResponse>>
            > getAllAppointments() {

        List<AppointmentResponse> response =
                appointmentService
                        .getAllAppointments();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Appointments retrieved successfully",
                        response
                )
        );
    }


    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<
            ApiResponse<List<AppointmentResponse>>
            > getDoctorAppointments(

            @PathVariable Long doctorId,

            @RequestParam
            @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE
            )
            LocalDate date
    ) {

        List<AppointmentResponse> response =
                appointmentService
                        .getDoctorAppointments(
                                doctorId,
                                date
                        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Doctor appointments retrieved successfully",
                        response
                )
        );
    }


    @GetMapping("/patient/{patientId}")
    public ResponseEntity<
            ApiResponse<List<AppointmentResponse>>
            > getPatientAppointments(

            @PathVariable Long patientId,

            @RequestParam
            @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE
            )
            LocalDate date
    ) {

        List<AppointmentResponse> response =
                appointmentService
                        .getPatientAppointments(
                                patientId,
                                date
                        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Patient appointments retrieved successfully",
                        response
                )
        );
    }
}
