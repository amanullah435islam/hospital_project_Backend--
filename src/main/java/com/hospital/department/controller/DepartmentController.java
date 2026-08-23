package com.hospital.department.controller;

import com.hospital.common.response.ApiResponse;
import com.hospital.department.dto.DepartmentCreateRequest;
import com.hospital.department.dto.DepartmentResponse;
import com.hospital.department.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponse>>
    createDepartment(
            @Valid @RequestBody
            DepartmentCreateRequest request
    ) {

        DepartmentResponse response =
                departmentService.createDepartment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Department created successfully",
                                response
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DepartmentResponse>>
    getDepartment(
            @PathVariable Long id
    ) {

        DepartmentResponse response =
                departmentService.getDepartmentById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Department retrieved successfully",
                        response
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>>
    getAllDepartments() {

        List<DepartmentResponse> response =
                departmentService.getAllDepartments();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Departments retrieved successfully",
                        response
                )
        );
    }
}
