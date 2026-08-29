package com.hospital.pharmacy.entity;

import com.hospital.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "stock_transactions",
        indexes = {
                @Index(
                        name = "idx_stock_transaction_batch",
                        columnList = "batch_id"
                ),
                @Index(
                        name = "idx_stock_transaction_date",
                        columnList = "transaction_date"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class StockTransaction extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "batch_id",
            nullable = false
    )
    private MedicineBatch batch;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "transaction_type",
            nullable = false,
            length = 30
    )
    private StockTransactionType transactionType;

    @Column(
            nullable = false
    )
    private Integer quantity;

    @Column(
            name = "transaction_date",
            nullable = false
    )
    private LocalDateTime transactionDate;

    @Column(
            length = 500
    )
    private String reason;
}
