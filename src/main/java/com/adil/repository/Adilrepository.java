package com.adil.repository;

import java.util.Date;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adil.model.User;

public interface Adilrepository extends JpaRepository<User, String>{
	
	@Query(nativeQuery = true, value="call saveAdil(:id,:dob,:mobile_numer,:name,:password,:registration_date)")
	public Map<String, String> saveAdil(String id, String dob, String mobile_numer, String name, String password, Date registration_date);
	
	

}
