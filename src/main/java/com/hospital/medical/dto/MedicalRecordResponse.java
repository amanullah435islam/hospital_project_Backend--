package com.hospital.medical.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
public class MedicalRecordResponse {

    private Long id;

    private String recordCode;

    private Long appointmentId;

    private String appointmentCode;

    private Long patientId;

    private String patientCode;

    private String patientName;

    private Long doctorId;

    private String doctorCode;

    private String doctorName;

    private String departmentName;

    private LocalDate visitDate;

    private String chiefComplaint;

    private String symptoms;

    private String diagnosis;

    private String clinicalNotes;

    private String treatmentPlan;

    private LocalDate followUpDate;
}
