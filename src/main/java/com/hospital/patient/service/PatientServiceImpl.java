package com.hospital.patient.service;

import com.hospital.common.exception.DuplicateResourceException;
import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.patient.dto.PatientCreateRequest;
import com.hospital.patient.dto.PatientResponse;
import com.hospital.patient.entity.Patient;
import com.hospital.patient.repository.PatientRepository;
import com.hospital.user.entity.User;
import com.hospital.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl
        implements PatientService {

    private final PatientRepository patientRepository;

    private final UserRepository userRepository;

    @Override
    public PatientResponse createPatient(
            PatientCreateRequest request
    ) {

        /*
         * Generate patient code
         */
        String patientCode =
                generatePatientCode();

        /*
         * Optional User
         */
        User user = null;

        if (request.getUserId() != null) {

            user = userRepository.findById(
                    request.getUserId()
            ).orElseThrow(() ->
                    new ResourceNotFoundException(
                            "User not found with id: "
                                    + request.getUserId()
                    )
            );

            /*
             * One User can belong to
             * maximum one Patient profile.
             */
            if (patientRepository
                    .findByUserId(request.getUserId())
                    .isPresent()) {

                throw new DuplicateResourceException(
                        "This user already has a patient profile"
                );
            }
        }

        /*
         * Create Patient
         */
        Patient patient = new Patient();

        patient.setPatientCode(patientCode);

        patient.setUser(user);

        patient.setFirstName(
                request.getFirstName().trim()
        );

        patient.setLastName(
                request.getLastName() != null
                        ? request.getLastName().trim()
                        : null
        );

        patient.setDateOfBirth(
                request.getDateOfBirth()
        );

        patient.setGender(
                request.getGender()
        );

        patient.setBloodGroup(
                request.getBloodGroup()
        );

        patient.setMaritalStatus(
                request.getMaritalStatus()
        );

        patient.setPhone(
                request.getPhone().trim()
        );

        patient.setEmail(
                request.getEmail()
        );

        patient.setAddress(
                request.getAddress()
        );

        patient.setEmergencyContactName(
                request.getEmergencyContactName()
        );

        patient.setEmergencyContactPhone(
                request.getEmergencyContactPhone()
        );

        patient.setOccupation(
                request.getOccupation()
        );

        Patient savedPatient =
                patientRepository.save(patient);

        return mapToResponse(savedPatient);
    }


    @Override
    @Transactional(readOnly = true)
    public PatientResponse getPatientById(
            Long id
    ) {

        Patient patient =
                patientRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Patient not found with id: "
                                                + id
                                )
                        );

        return mapToResponse(patient);
    }


    @Override
    @Transactional(readOnly = true)
    public PatientResponse getPatientByCode(
            String patientCode
    ) {

        Patient patient =
                patientRepository
                        .findByPatientCode(patientCode)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Patient not found with code: "
                                                + patientCode
                                )
                        );

        return mapToResponse(patient);
    }


    @Override
    @Transactional(readOnly = true)
    public List<PatientResponse> getAllPatients() {

        return patientRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    private PatientResponse mapToResponse(
            Patient patient
    ) {

        String fullName =
                patient.getFirstName()
                        + " "
                        + (
                        patient.getLastName() != null
                                ? patient.getLastName()
                                : ""
                );

        return PatientResponse.builder()
                .id(patient.getId())
                .patientCode(
                        patient.getPatientCode()
                )
                .userId(
                        patient.getUser() != null
                                ? patient.getUser().getId()
                                : null
                )
                .firstName(
                        patient.getFirstName()
                )
                .lastName(
                        patient.getLastName()
                )
                .fullName(
                        fullName.trim()
                )
                .dateOfBirth(
                        patient.getDateOfBirth()
                )
                .gender(
                        patient.getGender()
                )
                .bloodGroup(
                        patient.getBloodGroup()
                )
                .maritalStatus(
                        patient.getMaritalStatus()
                )
                .phone(
                        patient.getPhone()
                )
                .email(
                        patient.getEmail()
                )
                .address(
                        patient.getAddress()
                )
                .emergencyContactName(
                        patient.getEmergencyContactName()
                )
                .emergencyContactPhone(
                        patient.getEmergencyContactPhone()
                )
                .occupation(
                        patient.getOccupation()
                )
                .status(
                        patient.getStatus()
                )
                .build();
    }


    private String generatePatientCode() {

        long nextId =
                patientRepository.count() + 1;

        String code;

        do {

            code = String.format(
                    "PAT-%06d",
                    nextId
            );

            nextId++;

        } while (
                patientRepository
                        .existsByPatientCode(code)
        );

        return code;
    }
}
