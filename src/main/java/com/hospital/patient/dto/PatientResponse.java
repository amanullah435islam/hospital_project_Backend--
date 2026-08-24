package com.hospital.patient.dto;

import com.hospital.patient.entity.BloodGroup;
import com.hospital.patient.entity.Gender;
import com.hospital.patient.entity.MaritalStatus;
import com.hospital.patient.entity.PatientStatus;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
public class PatientResponse {

    private Long id;

    private String patientCode;

    private Long userId;

    private String firstName;

    private String lastName;

    private String fullName;

    private LocalDate dateOfBirth;

    private Gender gender;

    private BloodGroup bloodGroup;

    private MaritalStatus maritalStatus;

    private String phone;

    private String email;

    private String address;

    private String emergencyContactName;

    private String emergencyContactPhone;

    private String occupation;

    private PatientStatus status;
}
