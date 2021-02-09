package com.lti.repository;

import org.springframework.data.repository.Repository;

import com.lti.entity.LoginDetails;

public interface LoginRepository extends Repository<LoginDetails, Integer> {
	
	public LoginDetails findByEmailAndPassword(String email,String password);

	public LoginDetails findByEmail(String email);

}
