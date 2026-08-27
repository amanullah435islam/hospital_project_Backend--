package com.hospital.service.entity;

import com.hospital.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(
        name = "hospital_services",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_service_code",
                        columnNames = "service_code"
                )
        },
        indexes = {
                @Index(
                        name = "idx_service_name",
                        columnList = "name"
                ),
                @Index(
                        name = "idx_service_status",
                        columnList = "status"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class HospitalService extends BaseEntity {

    @Column(
            name = "service_code",
            nullable = false,
            unique = true,
            length = 30
    )
    private String serviceCode;

    @Column(
            nullable = false,
            length = 150
    )
    private String name;

    @Column(
            length = 500
    )
    private String description;

    @Column(
            nullable = false,
            precision = 12,
            scale = 2
    )
    private BigDecimal defaultPrice;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private ServiceStatus status =
            ServiceStatus.ACTIVE;
}
