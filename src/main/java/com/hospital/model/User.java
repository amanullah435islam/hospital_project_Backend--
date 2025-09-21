package com.hospital.model;

import java.util.Date;

import com.hospital.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// //normal & security code:::::
@Entity
@Table(name = "user")
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
	
	@Column(name = "password ")
    private String password ;
	
	@Column(name = "confirm_password ")
    private String confirmPassword ;

	
	@Enumerated(EnumType.STRING)
    private Role userRole;
	
	@Column(name = "image_url")
	private String imageUrl;
	

	@Column(name = "created_by_code")
    private String createdByCode ;

	@Column(name = "created_by_name")
    private String createdByName ;

	@Column(name = "created_at")
    private Date createdAt ;
	
	@Column(name = "updated_by_code")
    private String updatedByCode ;
	
	@Column(name = "updated_by_name")
    private String updatedByName ;

	@Column(name = "updated_at")
    private Date updatedAt ;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getCreatedByCode() {
		return createdByCode;
	}

	public void setCreatedByCode(String createdByCode) {
		this.createdByCode = createdByCode;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public String getUpdatedByCode() {
		return updatedByCode;
	}

	public void setUpdatedByCode(String updatedByCode) {
		this.updatedByCode = updatedByCode;
	}

	public String getUpdatedByName() {
		return updatedByName;
	}

	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}



