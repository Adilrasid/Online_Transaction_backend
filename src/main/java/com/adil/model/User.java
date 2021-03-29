package com.adil.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="User")
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="emailId")
	private String emailId;
	
	@Column(name="password")
	private String password;
	
	@Column(name="mobileNumer")
	private String mobileNumer;
	
	@Column(name="DOB")
	private String DOB;
	
	@CreationTimestamp
	@Column(name = "registrationDate")
	private LocalDateTime registeredDate;
	
	@CreationTimestamp
	@Column(name = "modifiedDate")
	private LocalDateTime modifiedDate;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String firstName, String lastName, String emailId,  String password, String mobileNumer, String DOB,
			LocalDateTime registeredDate,LocalDateTime modifiedDate ) {
		super();
		this.id = id;
		this.firstName =firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.mobileNumer = mobileNumer;
		this.DOB = DOB;
		this.registeredDate = registeredDate;
		this.modifiedDate = modifiedDate;
	}
	
	
	

}
