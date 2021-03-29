package com.adil.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationForm {

	// private int id;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@Email
	private String emailId;

	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}", message = "password has digit,lower case,upper case and special character must occur at least once,Password should be between 6 - 15 charactes")
	private String password;

	private String confirmPassword;

	@Size(min = 0, max = 10)
	private String mobileNumer;

	private String DOB;
	private Date registered_date;

	private Date modified_date;

}
