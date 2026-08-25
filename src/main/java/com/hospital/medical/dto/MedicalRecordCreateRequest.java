package com.hospital.medical.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class MedicalRecordCreateRequest {

    @NotNull(message = "Appointment ID is required")
    private Long appointmentId;

    @Size(max = 1000)
    private String chiefComplaint;

    @Size(max = 2000)
    private String symptoms;

    @Size(max = 2000)
    private String diagnosis;

    @Size(max = 5000)
    private String clinicalNotes;

    @Size(max = 3000)
    private String treatmentPlan;

    @FutureOrPresent(
            message = "Follow-up date cannot be in the past"
    )
    private LocalDate followUpDate;
}
