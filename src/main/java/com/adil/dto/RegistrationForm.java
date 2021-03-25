package com.adil.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
public class RegistrationForm {
	

	private String id;
	private String name;
	
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}",message = "password has digit,lower case,upper case and special character must occur at least once,Password should be between 6 - 15 charactes")
	private String password;
	
	@Pattern(regexp="(^$|[0-9]{10})")
	private String mobileNumer;
	
	private String DOB;
	private Date date;
	
	
		
	

}
