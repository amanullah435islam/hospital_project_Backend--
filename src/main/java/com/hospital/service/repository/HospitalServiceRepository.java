package com.hospital.service.repository;

import com.hospital.service.entity.HospitalService;
import com.hospital.service.entity.ServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HospitalServiceRepository
        extends JpaRepository<HospitalService, Long> {

    boolean existsByServiceCode(
            String serviceCode
    );

    boolean existsByNameIgnoreCase(
            String name
    );

    List<HospitalService>
    findByStatusOrderByNameAsc(
            ServiceStatus status
    );

    List<HospitalService>
    findByNameContainingIgnoreCaseOrderByNameAsc(
            String name
    );
}