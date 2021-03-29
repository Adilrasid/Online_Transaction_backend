package com.adil.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adil.model.User;

public interface Adilrepository extends JpaRepository<User, String> // repository for Registration

{

	@Query(nativeQuery = true, value = "call saveAdil(:id,:dob,:mobile_numer,:name,:password,:registration_date)")
	public Map<String, String> saveAdil(String id, String dob, String mobile_numer, String name, String password,
			Date registration_date);

	@Query(nativeQuery = true, value = "call loginAdil(:name,:password)")
	public Map<String, String> loginAdil(String name, String password);

	public Optional<User> findByEmailId(String emailId);
	
	public List<User> findById(long id);

	public String findByPassword(String pass);

}