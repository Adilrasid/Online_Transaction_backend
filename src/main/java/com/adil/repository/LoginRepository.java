package com.adil.repository;

import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adil.model.User;

	public interface LoginRepository extends JpaRepository<User, String>
	{
		
		@Query(nativeQuery = true, value="call loginAdil(:name,:password)")
		public Map<String, String> loginAdil(String name, String password);

	}


