package com.hospital.service.service;

import com.hospital.service.dto.ServiceCreateRequest;
import com.hospital.service.dto.ServiceResponse;
import com.hospital.service.dto.ServiceUpdateRequest;
import com.hospital.service.entity.ServiceStatus;
import java.util.List;

public interface HospitalServiceService {

    ServiceResponse createService(
            ServiceCreateRequest request
    );

    ServiceResponse getServiceById(
            Long id
    );

    List<ServiceResponse> getAllServices();

    List<ServiceResponse> getActiveServices();

    List<ServiceResponse> searchServices(
            String name
    );

    ServiceResponse updateService(
            Long id,
            ServiceUpdateRequest request
    );

    ServiceResponse updateServiceStatus(
            Long id,
            ServiceStatus status
    );
}
