package com.adil.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adil.dto.LoginForm;
import com.adil.repository.LoginRepository;

@RestController
@RequestMapping
public class Login {
	
	@Autowired
	LoginRepository loginRepo;
	
	@PostMapping("loginAdil")
	public ResponseEntity<Map<String, String>> loginAdil(@RequestBody LoginForm logi)
	{
		
		Map<String, String> adilConnection = new HashMap<String, String>();
		adilConnection = loginRepo.loginAdil(logi.getName(), logi.getPassword());
		
		Map<String, String> adilConnection1 = new HashMap<String, String>();
		adilConnection1.put("message",  logi.getName()+"you successfully Login");
		
		return new ResponseEntity<Map<String, String>>(adilConnection1, HttpStatus.OK);
	}
	
	

}
