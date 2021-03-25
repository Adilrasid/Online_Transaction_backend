package com.adil.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	
	@Column(name="mobileNumer")
	private String mobileNumer;
	
	@Column(name="DOB")
	private String DOB;
	
	@CreationTimestamp
	@Column(name = "registrationDate")
	private LocalDateTime registeredDate;
	

}
