package com.adil.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "otp_details")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OTP {
	
	@Id
	@GeneratedValue(generator = "UUID", strategy = GenerationType.SEQUENCE)
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Type(type = "uuid-char")
	@Column(name = "otp_id", updatable = false, nullable = false, length = 36)
	public UUID otpId;

	@Column(name = "otp_value", columnDefinition = "varchar(6)")
	public String otp_value;

	@Column(name = "status", columnDefinition = "tinyint(1) default 0")
	public int status;

	@Column(name = "phone_no", columnDefinition = "varchar(10) not null")
	public String phone_no;

	@Column(name = "created_date", columnDefinition = "DATETIME default CURRENT_TIMESTAMP")
	public Date createdDate;

	public OTP(UUID otpId, String otp_value, int status, String phone_no, Date createdDate) {
		super();
		this.otpId = otpId;
		this.otp_value = otp_value;
		this.status = status;
		this.phone_no = phone_no;
		this.createdDate = createdDate;
	}
	
	

}
