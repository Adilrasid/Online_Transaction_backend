package com.adil.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adil.dto.RegistrationForm;
import com.adil.repository.Adilrepository;
import com.adil.service.RegistrationService;

@RestController
@RequestMapping("/reg")
public class Registration {
	
	@Autowired
	RegistrationService registrationService;
	
	
	@Autowired
	Adilrepository adilRepo;
	
	/*@PostMapping("/conn")
	public String adil(@RequestParam String userName, @RequestParam String Password, @RequestParam String contact, @RequestParam String city)
	{
		return registrationService.Reg(userName, Password, contact , city);
	
	}*/
	
	@PostMapping("saveAdil")
	public ResponseEntity<Map<String, String>> saveAdil(@RequestBody RegistrationForm regi)
	{
		
		Map<String, String> adilConnection = new HashMap<String, String>();
		adilConnection = adilRepo.saveAdil(regi.getId(), regi.getDOB(), regi.getMobileNumer(), regi.getName(), regi.getPassword(),regi.getDate());
		
		Map<String, String> adilConnection1 = new HashMap<String, String>();
		adilConnection1.put("message", "Hi Adil your data inserted successfully");
		
		return new ResponseEntity<Map<String, String>>(adilConnection1, HttpStatus.OK);
	}
}