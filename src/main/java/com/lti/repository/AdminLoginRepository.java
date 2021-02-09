package com.lti.repository;

import org.springframework.data.repository.Repository;

import com.lti.entity.AdminLoginDetail;

public interface AdminLoginRepository extends Repository<AdminLoginDetail, Integer>{

	public AdminLoginDetail findByEmailAndPassword(String emailId,String password);

	public AdminLoginDetail findByEmail(String emailId);
}
