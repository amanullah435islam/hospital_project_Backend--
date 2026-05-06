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
@Table(name = "labTest")

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class LabTest {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	

	@Column(name = "test_code")
	private long testCode;
	
	@Column(name = "test_type")
	private String testType;
	
	@Column(name = "test_name")
	private String testName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "reported_by")
	private String reportedBy;
	

	@Column(name = "appointment_code")
	private int appointmentCode;
	
	@Column(name = "appointment_id")
	private int appointmentId;
		
	@Column(name = "prescription_code")
	private int prescriptionCode;
	
	@Column(name = "prescription_id")
	private long prescriptionId;

	@Column(name = "notes")
	private String notes;

	}

