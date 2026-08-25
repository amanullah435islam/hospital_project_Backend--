package com.hospital.medicine.repository;

import com.hospital.medicine.entity.Medicine;
import com.hospital.medicine.entity.MedicineStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MedicineRepository
        extends JpaRepository<Medicine, Long> {

    boolean existsByMedicineCode(
            String medicineCode
    );

    boolean existsByNameIgnoreCaseAndStrengthAndForm(
            String name,
            String strength,
            String form
    );

    Optional<Medicine> findByMedicineCode(
            String medicineCode
    );

    List<Medicine> findByStatus(
            MedicineStatus status
    );

    List<Medicine> findByNameContainingIgnoreCase(
            String name
    );
}