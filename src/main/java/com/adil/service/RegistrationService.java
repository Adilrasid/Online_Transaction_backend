package com.adil.service;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService
{
	private String userName;
	private String Password;
	private String city;
	private String contact;

	public String Reg(String userName, String Password, String city, String contact)
	{
		this.userName = userName;
		this.Password = Password;
		this.city = city;
		this.contact = contact;
		
		
		return userName;
		
	}
}


