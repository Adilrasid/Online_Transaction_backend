package com.adil.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adil.config.RabbitMQSender;
import com.adil.dto.LoginForm;
import com.adil.dto.RegistrationForm;
import com.adil.model.User;
import com.adil.repository.Adilrepository;
import com.adil.response.Response;
import com.adil.service.AdilService;

@RestController
public class AdilController {

	@Autowired
	AdilService registrationService;

	@Autowired
	Adilrepository adilRepo;

	/*
	 * @PostMapping("/conn") public String adil(@RequestParam String
	 * userName, @RequestParam String Password, @RequestParam String
	 * contact, @RequestParam String city) { return
	 * registrationService.Reg(userName, Password, contact , city);
	 * 
	 * }
	 */

//	@PostMapping("saveAdil")
//	public ResponseEntity<Map<String, String>> saveAdil(@RequestBody RegistrationForm regi)
//	{
//		
//		Map<String, String> adilConnection = new HashMap<String, String>();
//		adilConnection = adilRepo.saveAdil(regi.getId(), regi.getDOB(), regi.getMobileNumer(), regi.getName(), regi.getPassword(),regi.getDate());
//		
//		Map<String, String> adilConnection1 = new HashMap<String, String>();
//		adilConnection1.put("message", "Hi Adil your data inserted successfully");
//		
//		return new ResponseEntity<Map<String, String>>(adilConnection1, HttpStatus.OK);
//	}

	@RequestMapping("/adil")
	public String adil(@RequestParam String userName, @RequestParam String Password) {
		return registrationService.adil(userName, Password);

	}

	@PostMapping("loginAdil")
	public ResponseEntity<Map<String, String>> loginAdil(@RequestBody LoginForm logi) {

		Map<String, String> adilConnection = new HashMap<String, String>();
		adilConnection = adilRepo.loginAdil(logi.getEmailId(), logi.getPassword());

		Map<String, String> adilConnection1 = new HashMap<String, String>();
		adilConnection1.put("message", logi.getEmailId() + "you successfully Login");

		return new ResponseEntity<Map<String, String>>(adilConnection1, HttpStatus.OK);
	}

	@PostMapping("/JpaAdil")
	public ResponseEntity<Response> register(@RequestBody RegistrationForm regii) {
		Response res = registrationService.register(regii);

		return new ResponseEntity<Response>(res, HttpStatus.OK);
	}

	@PostMapping("/JpaLogin")
	public ResponseEntity<Response> login(@RequestBody LoginForm Logi) {
		Response res = registrationService.login(Logi);
		return new ResponseEntity<Response>(res, HttpStatus.OK);
	}

	// @PostMapping("/jpaUpdate")

	@GetMapping("/getAllUser")
	public List<User> getALLUser(@RequestParam String token) {
		List<User> ls = registrationService.getALLUser(token);
		return ls;
	}

	@RequestMapping("/verifyUser/{token}")
	public ResponseEntity<Response> verifyUser(@PathVariable String token) {
		Response res = registrationService.verifyUser(token);
		return new ResponseEntity<Response>(res, HttpStatus.OK);

	}
}