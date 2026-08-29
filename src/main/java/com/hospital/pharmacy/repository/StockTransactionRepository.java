package com.hospital.pharmacy.repository;

import com.hospital.pharmacy.entity.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StockTransactionRepository
        extends JpaRepository<
        StockTransaction,
        Long
        > {

    List<StockTransaction>
    findByBatchIdOrderByTransactionDateDesc(
            Long batchId
    );
}
