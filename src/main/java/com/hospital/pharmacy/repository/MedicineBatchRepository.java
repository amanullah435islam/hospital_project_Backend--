package com.hospital.pharmacy.repository;

import com.hospital.pharmacy.entity.MedicineBatch;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface MedicineBatchRepository
        extends JpaRepository<MedicineBatch, Long> {

    boolean existsByMedicineIdAndBatchNumber(
            Long medicineId,
            String batchNumber
    );

    List<MedicineBatch>
    findByMedicineIdOrderByExpiryDateAsc(
            Long medicineId
    );

    List<MedicineBatch>
    findByQuantityGreaterThanAndExpiryDateAfterOrderByExpiryDateAsc(
            Integer quantity,
            LocalDate date
    );
}