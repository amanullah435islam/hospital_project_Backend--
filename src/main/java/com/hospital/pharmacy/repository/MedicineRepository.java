package com.hospital.pharmacy.repository;

import com.hospital.pharmacy.entity.Medicine;
import com.hospital.pharmacy.entity.MedicineStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicineRepository
        extends JpaRepository<Medicine, Long> {

    boolean existsByMedicineCode(
            String medicineCode
    );

    boolean existsByNameIgnoreCaseAndStrengthIgnoreCase(
            String name,
            String strength
    );

    List<Medicine>
    findByStatusOrderByNameAsc(
            MedicineStatus status
    );

    List<Medicine>
    findByNameContainingIgnoreCaseOrderByNameAsc(
            String name
    );
}
