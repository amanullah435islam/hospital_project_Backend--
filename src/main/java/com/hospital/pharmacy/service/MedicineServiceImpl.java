package com.hospital.pharmacy.service;

import com.hospital.common.exception.DuplicateResourceException;
import com.hospital.common.exception.ResourceNotFoundException;
import com.hospital.pharmacy.dto.MedicineCreateRequest;
import com.hospital.pharmacy.dto.MedicineResponse;
import com.hospital.pharmacy.entity.Medicine;
import com.hospital.pharmacy.entity.MedicineStatus;
import com.hospital.pharmacy.repository.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicineServiceImpl
        implements MedicineService {

    private final MedicineRepository
            medicineRepository;


    @Override
    public MedicineResponse createMedicine(
            MedicineCreateRequest request
    ) {

        if (
                medicineRepository
                        .existsByNameIgnoreCaseAndStrengthIgnoreCase(
                                request.getName().trim(),
                                request.getStrength()
                        )
        ) {

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
                request.getName().trim()
        );

        medicine.setGenericName(
                request.getGenericName()
        );

        medicine.setStrength(
                request.getStrength()
        );

        medicine.setDosageForm(
                request.getDosageForm()
        );

        medicine.setStatus(
                MedicineStatus.ACTIVE
        );


        Medicine saved =
                medicineRepository.save(
                        medicine
                );


        return mapToResponse(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public MedicineResponse getMedicineById(
            Long id
    ) {

        return mapToResponse(
                findMedicine(id)
        );
    }


    @Override
    @Transactional(readOnly = true)
    public List<MedicineResponse>
    getAllMedicines() {

        return medicineRepository
                .findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<MedicineResponse>
    getActiveMedicines() {

        return medicineRepository
                .findByStatusOrderByNameAsc(
                        MedicineStatus.ACTIVE
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<MedicineResponse>
    searchMedicines(
            String name
    ) {

        if (
                name == null
                        || name.isBlank()
        ) {

            return getActiveMedicines();
        }


        return medicineRepository
                .findByNameContainingIgnoreCaseOrderByNameAsc(
                        name.trim()
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    private Medicine findMedicine(
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


    private MedicineResponse mapToResponse(
            Medicine medicine
    ) {

        return MedicineResponse
                .builder()
                .id(medicine.getId())
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
                .dosageForm(
                        medicine.getDosageForm()
                )
                .status(
                        medicine.getStatus()
                )
                .build();
    }
}