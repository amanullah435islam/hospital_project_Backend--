package com.hospital.medicine.service;

import com.hospital.common.exception.BadRequestException;
import com.hospital.common.exception.DuplicateResourceException;
import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.medicine.dto.MedicineCreateRequest;
import com.hospital.medicine.dto.MedicineResponse;
import com.hospital.medicine.dto.MedicineUpdateRequest;
import com.hospital.medicine.entity.Medicine;
import com.hospital.medicine.entity.MedicineStatus;
import com.hospital.medicine.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicineServiceImpl
        implements MedicineService {

    private final MedicineRepository medicineRepository;


    // ==========================================
    // CREATE
    // ==========================================

    @Override
    public MedicineResponse createMedicine(
            MedicineCreateRequest request
    ) {

        /*
         * Prevent duplicate medicine
         * based on name + strength + form.
         */

        boolean alreadyExists =
                medicineRepository
                        .existsByNameIgnoreCaseAndStrengthAndForm(
                                request.getName(),
                                request.getStrength(),
                                request.getForm()
                        );

        if (alreadyExists) {

            throw new DuplicateResourceException(
                    "Medicine already exists: "
                            + request.getName()
            );
        }


        Medicine medicine =
                new Medicine();

        medicine.setMedicineCode(
                generateMedicineCode()
        );

        medicine.setName(
                request.getName()
        );

        medicine.setGenericName(
                request.getGenericName()
        );

        medicine.setStrength(
                request.getStrength()
        );

        medicine.setForm(
                request.getForm()
        );

        medicine.setStatus(
                request.getStatus() != null
                        ? request.getStatus()
                        : MedicineStatus.ACTIVE
        );


        Medicine savedMedicine =
                medicineRepository.save(
                        medicine
                );


        return mapToResponse(
                savedMedicine
        );
    }


    // ==========================================
    // GET BY ID
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public MedicineResponse getMedicineById(
            Long id
    ) {

        Medicine medicine =
                findMedicineById(id);

        return mapToResponse(
                medicine
        );
    }


    // ==========================================
    // GET ALL
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public List<MedicineResponse> getAllMedicines() {

        return medicineRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // ==========================================
    // SEARCH
    // ==========================================

    @Override
    @Transactional(readOnly = true)
    public List<MedicineResponse> searchMedicines(
            String name
    ) {

        if (name == null || name.isBlank()) {

            return getAllMedicines();
        }


        return medicineRepository
                .findByNameContainingIgnoreCase(
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
    public MedicineResponse updateMedicine(
            Long id,
            MedicineUpdateRequest request
    ) {

        Medicine medicine =
                findMedicineById(id);


        /*
         * Update only provided fields.
         */

        if (request.getName() != null
                && !request.getName().isBlank()) {

            medicine.setName(
                    request.getName().trim()
            );
        }


        if (request.getGenericName() != null) {

            medicine.setGenericName(
                    request.getGenericName().trim()
            );
        }


        if (request.getStrength() != null) {

            medicine.setStrength(
                    request.getStrength().trim()
            );
        }


        if (request.getForm() != null) {

            medicine.setForm(
                    request.getForm().trim()
            );
        }


        if (request.getStatus() != null) {

            medicine.setStatus(
                    request.getStatus()
            );
        }


        Medicine updatedMedicine =
                medicineRepository.save(
                        medicine
                );


        return mapToResponse(
                updatedMedicine
        );
    }


    // ==========================================
    // UPDATE STATUS
    // ==========================================

    @Override
    public MedicineResponse updateMedicineStatus(
            Long id,
            MedicineStatus status
    ) {

        if (status == null) {

            throw new BadRequestException(
                    "Medicine status is required"
            );
        }


        Medicine medicine =
                findMedicineById(id);


        medicine.setStatus(
                status
        );


        return mapToResponse(
                medicine
        );
    }


    // ==========================================
    // FIND MEDICINE
    // ==========================================

    private Medicine findMedicineById(
            Long id
    ) {

        return medicineRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Medicine not found with id: "
                                        + id
                        )
                );
    }


    // ==========================================
    // CODE GENERATOR
    // ==========================================

    private String generateMedicineCode() {

        long nextId =
                medicineRepository.count() + 1;

        String code;

        do {

            code = String.format(
                    "MED-%06d",
                    nextId
            );

            nextId++;

        } while (
                medicineRepository
                        .existsByMedicineCode(code)
        );

        return code;
    }


    // ==========================================
    // ENTITY → RESPONSE
    // ==========================================

    private MedicineResponse mapToResponse(
            Medicine medicine
    ) {

        return MedicineResponse
                .builder()
                .id(
                        medicine.getId()
                )
                .medicineCode(
                        medicine.getMedicineCode()
                )
                .name(
                        medicine.getName()
                )
                .genericName(
                        medicine.getGenericName()
                )
                .strength(
                        medicine.getStrength()
                )
                .form(
                        medicine.getForm()
                )
                .status(
                        medicine.getStatus()
                )
                .build();
    }
}
