package com.hospital.department.service;

import com.hospital.department.dto.DepartmentCreateRequest;
import com.hospital.department.dto.DepartmentResponse;

import java.util.List;

public interface DepartmentService {

    DepartmentResponse createDepartment(
            DepartmentCreateRequest request
    );

    DepartmentResponse getDepartmentById(Long id);

    List<DepartmentResponse> getAllDepartments();
}
