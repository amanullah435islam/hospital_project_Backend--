package com.hospital.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "patient")

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	

	@Column(name = "patient_code")
	private int patientCode;
	
	@Column(name = "patient_name")
	private String patientName;
	

	@Column(name = "visit_amount") 
	private int visitAmount;


	@Column(name = "age")
	private int age;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dob;

	@Column(name = "gender")
	private String gender;

	@Column(name = "phone")
	private String phone;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date lastVisit;
	
}


