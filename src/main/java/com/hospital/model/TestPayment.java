package com.hospital.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "testPayment")

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class TestPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testCode;
    private Long patientId;
    private Double amount;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

}
