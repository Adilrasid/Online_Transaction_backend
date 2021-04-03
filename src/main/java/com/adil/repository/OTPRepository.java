package com.adil.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adil.model.OTP;

@Repository
public interface OTPRepository extends JpaRepository<OTP,UUID>
{
//	public OTP saveOtp(OTP otp);
	
}
