package com.adil.service;

import org.springframework.stereotype.Service;

@Service
public class AdilService {
	
	public String adil(String userName , String Password)
	{
		if(userName.equalsIgnoreCase("Adil") && Password.equalsIgnoreCase("1234"))
		{
			return "login success";
		}
		
		else
		{
			return "invalid credential";
		}
		
	}
	
	
}
