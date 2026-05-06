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
@Table(name = "appointment")

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "appointment_code")
	private int appointmentCode;

	
	@Column(name = "patient_id")
	private int patientId;
	
	@Column(name = "patient_code")
	private int patientCode;

	@Column(name = "patient_name")
	private String patientName;

	@Column(name = "doctor_id")
	private int doctorId;
	
	@Column(name = "doctor_code")
	private int doctorCode;

	@Column(name = "doctor_name")
	private String doctorName;

	@Column(name = "date")
	private String date;

	@Column(name = "department")
	private String department;

	@Column(name = "status")
	private int status;

	@Column(name = "madicleHistry")
	private String madicleHistry;

	@Column(name = "bookingDate")
	private String bookingDate;

	@Column(name = "paymentStatus")
	private String paymentStatus;

}
