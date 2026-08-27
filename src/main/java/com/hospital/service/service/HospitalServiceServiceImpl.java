package com.hospital.service.service;

import com.hospital.common.exception.BadRequestException;
import com.hospital.common.exception.DuplicateResourceException;
import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.service.dto.ServiceCreateRequest;
import com.hospital.service.dto.ServiceResponse;
import com.hospital.service.dto.ServiceUpdateRequest;
import com.hospital.service.entity.HospitalService;
import com.hospital.service.entity.ServiceStatus;
import com.hospital.service.repository.HospitalServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HospitalServiceServiceImpl
        implements HospitalServiceService {

    private final HospitalServiceRepository
            serviceRepository;


    // ==========================================
    // CREATE
    // ==========================================

    @Override
    public ServiceResponse createService(
            ServiceCreateRequest request
    ) {

        if (
                serviceRepository
                        .existsByNameIgnoreCase(
                                request.getName()
                        )
        ) {

            throw new DuplicateResourceException(
                    "Hospital service already exists: "
                            + request.getName()
            );
        }


        HospitalService service =
                new HospitalService();

        service.setServiceCode(
                generateServiceCode()
        );

        service.setName(
                request.getName().trim()
        );

        service.setDescription(
                request.getDescription()
        );

        service.setDefaultPrice(
                request.getDefaultPrice()
        );

        service.setStatus(
                ServiceStatus.ACTIVE
        );


        HospitalService saved =
                serviceRepository.save(service);


        return mapToResponse(saved);
    }


    // ==========================================
    // GET BY ID
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public ServiceResponse getServiceById(
            Long id
    ) {

        HospitalService service =
                findService(id);

        return mapToResponse(service);
    }


    // ==========================================
    // GET ALL
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public List<ServiceResponse> getAllServices() {

        return serviceRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // ==========================================
    // GET ACTIVE
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public List<ServiceResponse>
    getActiveServices() {

        return serviceRepository
                .findByStatusOrderByNameAsc(
                        ServiceStatus.ACTIVE
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // ==========================================
    // SEARCH
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public List<ServiceResponse> searchServices(
            String name
    ) {

        if (
                name == null
                        || name.isBlank()
        ) {

            return getActiveServices();
        }


        return serviceRepository
                .findByNameContainingIgnoreCaseOrderByNameAsc(
                        name.trim()
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // ==========================================
    // UPDATE
    // ==========================================

    @Override
    public ServiceResponse updateService(
            Long id,
            ServiceUpdateRequest request
    ) {

        HospitalService service =
                findService(id);


        if (
                request.getName() != null
                        && !request.getName().isBlank()
        ) {

            String newName =
                    request.getName().trim();


            if (
                    !newName.equalsIgnoreCase(
                            service.getName()
                    )
                            &&
                            serviceRepository
                                    .existsByNameIgnoreCase(
                                            newName
                                    )
            ) {

                throw new DuplicateResourceException(
                        "Hospital service already exists: "
                                + newName
                );
            }


            service.setName(newName);
        }


        if (
                request.getDescription()
                        != null
        ) {

            service.setDescription(
                    request.getDescription()
            );
        }


        if (
                request.getDefaultPrice()
                        != null
        ) {

            service.setDefaultPrice(
                    request.getDefaultPrice()
            );
        }


        if (
                request.getStatus()
                        != null
        ) {

            service.setStatus(
                    request.getStatus()
            );
        }


        return mapToResponse(service);
    }


    // ==========================================
    // UPDATE STATUS
    // ==========================================

    @Override
    public ServiceResponse updateServiceStatus(
            Long id,
            ServiceStatus status
    ) {

        if (status == null) {

            throw new BadRequestException(
                    "Service status is required"
            );
        }


        HospitalService service =
                findService(id);


        service.setStatus(status);


        return mapToResponse(service);
    }


    // ==========================================
    // FIND
    // ==========================================

    private HospitalService findService(
            Long id
    ) {

        return serviceRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hospital service not found with id: "
                                        + id
                        )
                );
    }


    // ==========================================
    // CODE GENERATOR
    // ==========================================

    private String generateServiceCode() {

        long nextId =
                serviceRepository.count() + 1;

        String code;

        do {

            code = String.format(
                    "SRV-%06d",
                    nextId
            );

            nextId++;

        } while (
                serviceRepository
                        .existsByServiceCode(code)
        );

        return code;
    }


    // ==========================================
    // MAPPER
    // ==========================================

    private ServiceResponse mapToResponse(
            HospitalService service
    ) {

        return ServiceResponse
                .builder()
                .id(service.getId())
                .serviceCode(
                        service.getServiceCode()
                )
                .name(
                        service.getName()
                )
                .description(
                        service.getDescription()
                )
                .defaultPrice(
                        service.getDefaultPrice()
                )
                .status(
                        service.getStatus()
                )
                .build();
    }
}
