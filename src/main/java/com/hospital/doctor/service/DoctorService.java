package com.hospital.doctor.service;

import com.hospital.doctor.dto.DoctorCreateRequest;
import com.hospital.doctor.dto.DoctorResponse;
import java.util.List;

public interface DoctorService {

    DoctorResponse createDoctor(
            DoctorCreateRequest request
    );

    DoctorResponse getDoctorById(Long id);

    List<DoctorResponse> getAllDoctors();
}
