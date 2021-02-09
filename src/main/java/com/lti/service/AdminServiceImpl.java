package com.lti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.AdminSearchStudent;
import com.lti.entity.AdminLoginDetail;
import com.lti.entity.Questions;
import com.lti.entity.UserDetails;
import com.lti.exception.UserServiceException;
import com.lti.repository.AdminLoginRepository;
import com.lti.repository.QuestionsRepository;
import com.lti.repository.ReportRepository;
import com.lti.repository.ReportsDao;
import com.lti.repository.UserRepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private QuestionsRepository quesRepo;
	
	@Autowired
	private AdminLoginRepository adminLoginRepository;
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private ReportsDao reportsDao;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Questions insert(Questions question) {
		return quesRepo.save(question);
	}

	@Override
	public void delete(int id) {
		quesRepo.deleteById(id);		
	}

	@Override
	public AdminLoginDetail login(String email, String password) throws UserServiceException{
		AdminLoginDetail adminUser = adminLoginRepository.findByEmailAndPassword(email, password);
		if(adminUser==null)
			throw new UserServiceException("Incorrect Email or Password");
		return adminUser;
	}

	@Override
	@Transactional
	public List<UserDetails> searchStudents(AdminSearchStudent adminSearchStudent) {
		if(adminSearchStudent.getLevel() == 1) {
			List<UserDetails> list = userRepository.findAllUsersLevel1(adminSearchStudent.getTechnology(),
					adminSearchStudent.getState(), adminSearchStudent.getCity(), adminSearchStudent.getMarks());
			return list;
		} else if(adminSearchStudent.getLevel() == 2) {
			List<UserDetails> list = userRepository.findAllUsersLevel2(adminSearchStudent.getTechnology(), 
					adminSearchStudent.getState(), adminSearchStudent.getCity(), adminSearchStudent.getMarks());
			return list;
		} else {
			List<UserDetails> list = userRepository.findAllUsersLevel3(adminSearchStudent.getTechnology(), 
					adminSearchStudent.getState(), adminSearchStudent.getCity(), adminSearchStudent.getMarks());
			return list;
		}
	}

}

