package com.adil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adil.service.AdilService;

@RestController
@RequestMapping("/adil")
public class Connection {
	
	@Autowired
	AdilService adilService;
	
	@GetMapping("/conn")
	public String adil(@RequestParam String userName, @RequestParam String Password)
	{
		return adilService.adil(userName, Password);
	
	}

}
