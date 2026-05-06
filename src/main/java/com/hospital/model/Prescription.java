package com.hospital.model;

import java.util.Date;
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
	@Table(name = "prescription")
	
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	@Data
	public class Prescription {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		@Column(name = "prescription_code")
		private Number prescriptionCode;
		
		//patient details
		@Column(name = "patient_id")
		private long patientId;
		
		@Column(name = "patient_code")
		private int patientCode;
		
		@Column(name = "patient_name")
		private String patientName;
		
		@Column(name = "age")
		private int age;

		@Column(name = "dob")
		private Date dob;

		@Column(name = "gender")
		private String gender;

		@Column(name = "phone")
		private String phone;

		@Column(name = "lastVisit")
		private Date lastVisit;
		
		
		//doctor details
		@Column(name = "doctor_id")
		private int doctorId;
		
		@Column(name = "doctor_code")
		private int doctorCode;
		
		@Column(name = "doctor_name")
		private String doctorName;
		
		@Column(name = "specialize")
		private String specialize;

		@Column(name = "contact")
		private String contact;

		@Column(name = "availability")
		private String availability;

		@Column(name = "email")
		private String email;

		@Column(name = "roomNumber")
		private String roomNumber;
		
		
		//appointment details
		@Column(name = "appointment_id")
		private int appointmentId;

		@Column(name = "appointment_code")
		private int appointmentCode;
		
		@Column(name = "date")
		private Date date;

		@Column(name = "department")
		private String department;

		@Column(name = "status")
		private Integer status;

		@Column(name = "madicleHistry")
		private String madicleHistry;

		@Column(name = "bookingDate")
		private Date bookingDate;

		@Column(name = "paymentStatus")
		private String paymentStatus;

		}