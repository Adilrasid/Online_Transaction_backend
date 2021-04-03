package com.adil.controller;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adil.Exception.UserDoesNotExitException;
import com.adil.model.OTP;
import com.adil.repository.OTPRepository;
import com.adil.response.Response;
import com.adil.service.OTPService;

@RestController 
public class OTPController {
	
	@Autowired
	private OTPRepository otpRepo;
	
	@PostMapping("/generateOTP")
	public  ResponseEntity<?> generateNewOtp(@RequestBody OTP otp)
	{
		if(validateMobileNumber(otp.phone_no))
		{
			String newOtp = RandomStringUtils.randomNumeric(6);
			otp.setOtpId(UUID.randomUUID());
			otp.setStatus(0);
			otp.setCreatedDate(new Date());
			otp.setOtp_value(newOtp);
			OTP o = otpRepo.save(otp);
			
			/*HashMap<String,String> otpData = new HashMap<String,String>();
			otpData.put("otp", newOtp);
			String body = getMessage(otpData);
			if(body.length()>0)
			{
				String SmsResp = sendSms(otp.phone_no,body);
			}*/
			return  ResponseEntity.ok(o);
			
		}
		return ResponseEntity.ok(new UserDoesNotExitException("Bhagg"));
		
		
	}	
	private static boolean validateMobileNumber(String mobileNo) {
		Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
		Matcher m = p.matcher(mobileNo);
		return (m.find() && m.group().equals(mobileNo));
	}
	/*public String getMessage(Map<String,String> data)
	{
		String body ="";
		Writer out = null;
		Configuration cfg = new Configuration();
		cfg.setD
		
	}*/

}
