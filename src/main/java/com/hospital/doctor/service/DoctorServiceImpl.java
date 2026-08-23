package com.hospital.doctor.service;

import com.hospital.department.entity.Department;
import com.hospital.department.repository.DepartmentRepository;
import com.hospital.doctor.dto.DoctorCreateRequest;
import com.hospital.doctor.dto.DoctorResponse;
import com.hospital.doctor.entity.Doctor;
import com.hospital.doctor.repository.DoctorRepository;
import com.hospital.user.entity.User;
import com.hospital.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl
        implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final UserRepository userRepository;

    private final DepartmentRepository departmentRepository;

    @Override
    public DoctorResponse createDoctor(
            DoctorCreateRequest request
    ) {

        if (doctorRepository.existsByDoctorCode(
                request.getDoctorCode()
        )) {
            throw new IllegalArgumentException(
                    "Doctor code already exists"
            );
        }

        if (doctorRepository.existsByLicenseNumber(
                request.getLicenseNumber()
        )) {
            throw new IllegalArgumentException(
                    "License number already exists"
            );
        }

        if (doctorRepository.existsByUserId(
                request.getUserId()
        )) {
            throw new IllegalArgumentException(
                    "This user already has a doctor profile"
            );
        }

        User user = userRepository.findById(
                request.getUserId()
        ).orElseThrow(() ->
                new IllegalArgumentException(
                        "User not found with id: "
                                + request.getUserId()
                )
        );

        Department department =
                departmentRepository.findById(
                        request.getDepartmentId()
                ).orElseThrow(() ->
                        new IllegalArgumentException(
                                "Department not found with id: "
                                        + request.getDepartmentId()
                        )
                );

        Doctor doctor = new Doctor();

        doctor.setUser(user);
        doctor.setDepartment(department);

        doctor.setDoctorCode(
                request.getDoctorCode().trim()
        );

        doctor.setSpecialization(
                request.getSpecialization().trim()
        );

        doctor.setQualification(
                request.getQualification()
        );

        doctor.setLicenseNumber(
                request.getLicenseNumber().trim()
        );

        doctor.setExperienceYears(
                request.getExperienceYears()
        );

        doctor.setConsultationFee(
                request.getConsultationFee()
        );

        Doctor savedDoctor =
                doctorRepository.save(doctor);

        return mapToResponse(savedDoctor);
    }

    @Override
    @Transactional(readOnly = true)
    public DoctorResponse getDoctorById(Long id) {

        Doctor doctor =
                doctorRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Doctor not found with id: "
                                                + id
                                )
                        );

        return mapToResponse(doctor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorResponse> getAllDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private DoctorResponse mapToResponse(
            Doctor doctor
    ) {

        User user = doctor.getUser();

        Department department =
                doctor.getDepartment();

        String doctorName =
                user.getFirstName()
                        + " "
                        + user.getLastName();

        return DoctorResponse.builder()
                .id(doctor.getId())
                .doctorCode(doctor.getDoctorCode())
                .userId(user.getId())
                .doctorName(doctorName.trim())
                .email(user.getEmail())
                .departmentId(department.getId())
                .departmentName(department.getName())
                .specialization(
                        doctor.getSpecialization()
                )
                .qualification(
                        doctor.getQualification()
                )
                .licenseNumber(
                        doctor.getLicenseNumber()
                )
                .experienceYears(
                        doctor.getExperienceYears()
                )
                .consultationFee(
                        doctor.getConsultationFee()
                )
                .status(doctor.getStatus())
                .build();
    }
}
