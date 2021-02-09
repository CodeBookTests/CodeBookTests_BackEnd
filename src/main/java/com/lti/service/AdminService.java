package com.lti.service;

import java.util.List;

import com.lti.dto.AdminSearchStudent;
import com.lti.entity.AdminLoginDetail;
import com.lti.entity.Questions;
import com.lti.entity.UserDetails;
import com.lti.exception.UserServiceException;

public interface AdminService {

	public Questions insert(Questions question);
	
	public void delete(int id);

	public AdminLoginDetail login(String email, String password)  throws UserServiceException;
	
	public List<UserDetails> searchStudents(AdminSearchStudent adminSearchStudent);
}
