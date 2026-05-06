package com.hospital.model;

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
@Table(name = "medicine")

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Medicine {
		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
	@Column(name = "medicine_code")
	private long medicineCode;
	
	@Column(name = "medicine_name")
	private String medicineName;
	

    
	@Column(name = "appointment_code")
	private int appointmentCode;
	
	@Column(name = "appointment_id")
	private int appointmentId;
		
	@Column(name = "prescription_code")
	private int prescriptionCode;
	
	@Column(name = "prescription_id")
	private long prescriptionId;
	
	@Column(name = "dose")
	private String dose;
	
	@Column(name = "frequency")
	private String frequency;
	
	@Column(name = "duration")
	private String duration;
	
	} 