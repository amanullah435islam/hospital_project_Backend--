package com.hospital.department.dto;

import com.hospital.department.entity.DepartmentStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepartmentResponse {

    private Long id;

    private String name;

    private String description;

    private DepartmentStatus status;
}
