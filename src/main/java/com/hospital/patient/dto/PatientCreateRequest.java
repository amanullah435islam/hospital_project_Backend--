package com.hospital.patient.dto;

import com.hospital.patient.entity.BloodGroup;
import com.hospital.patient.entity.Gender;
import com.hospital.patient.entity.MaritalStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PatientCreateRequest {

    /*
     * Optional.
     *
     * Patient can be registered
     * without an online user account.
     */
    private Long userId;

    @NotBlank(message = "First name is required")
    @Size(max = 100)
    private String firstName;

    @Size(max = 100)
    private String lastName;

    @NotNull(message = "Date of birth is required")
    @Past(
            message = "Date of birth must be in the past"
    )
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is required")
    private Gender gender;

    private BloodGroup bloodGroup;

    private MaritalStatus maritalStatus;

    @NotBlank(message = "Phone is required")
    @Size(max = 20)
    private String phone;

    @Email(message = "Invalid email format")
    private String email;

    @Size(max = 500)
    private String address;

    @Size(max = 150)
    private String emergencyContactName;

    @Size(max = 20)
    private String emergencyContactPhone;

    @Size(max = 100)
    private String occupation;
}
