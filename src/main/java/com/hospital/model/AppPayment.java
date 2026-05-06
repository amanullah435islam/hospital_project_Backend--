package com.hospital.model;

import java.util.Date;
import com.hospital.enums.PaymentMethod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

	@Entity
	@Table(name = "appPayment")
	
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	@Data
	public class AppPayment {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;

		@Column(name = "payment_code")
		private int paymentCode;

		@Column(name = "patient_id")
		private int patientId;
		
		@Column(name = "patient_name")
		private String patientName;

		@Column(name = "paymentDate")
		private Date paymentDate;

		@Column(name = "amount")
		private int amount;

		@Enumerated(EnumType.STRING)
		private PaymentMethod paymentMethod;
		
		@Column(name = "mobile_number")
		private String mobileNumber;

		@Column(name = "transaction_id")
		private String transactionId;

		@Column(name = "card_number")
		private String cardNumber;

		@Column(name = "card_expiry")
		private String cardExpiry;

	}
