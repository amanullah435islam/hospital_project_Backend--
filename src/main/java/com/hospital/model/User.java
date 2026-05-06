package com.hospital.model;

import com.hospital.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user")


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


	@Column(name = "user_code")
    private int userCode ;
	
	@Column(name = "name")
    private String name ;
	
	@Column(name = "user_name")
    private String username ;
	
	@Column(name = "email")
    private String email ;
	
	@Column(name = "password")
    private String password;
	
	@Transient
	private String confirmPassword;

	
	@Enumerated(EnumType.STRING)
    private Role userRole;
	
	@Column(name = "image_url")
	private String imageUrl;	

}



