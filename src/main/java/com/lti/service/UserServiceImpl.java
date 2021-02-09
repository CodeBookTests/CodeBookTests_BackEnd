package com.lti.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.ReportCompKey;
import com.lti.dto.SubmitResponse;
import com.lti.entity.LoginDetails;
import com.lti.entity.Reports;
import com.lti.entity.UserDetails;
import com.lti.exception.UserServiceException;
import com.lti.repository.LoginRepository;
import com.lti.repository.QuestionsRepository;
import com.lti.repository.ReportRepository;
import com.lti.repository.ReportsDao;
import com.lti.repository.UserPasswordRepo;
import com.lti.repository.UserRepository;


@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoginRepository loginRepo;
	
//	@Autowired
//	private UserResponseRepository userResponseRepository;
	
	@Autowired
	private QuestionsRepository quesRepo;
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private Reports report;
	
	@Autowired
	private ReportCompKey reportCompositeKey;
	
	@Autowired
	private ReportsDao reportDao;
	
	@Autowired
	private UserPasswordRepo userPassRepo;
	
//	@Autowired
//	private UserResponse userResponse;
	
	@Transactional
	public int register(UserDetails user) {
		LoginDetails userExists = loginRepo.findByEmail(user.getEmail());
		if(userExists!=null) {
			throw new UserServiceException("User already registered!");
		}
		UserDetails updatedUser = (UserDetails) userRepository.save(user);
		return updatedUser.getUser_id();


	}
	
	@Transactional
	public LoginDetails login(String email, String password) {
			LoginDetails user = loginRepo.findByEmailAndPassword(email,password);
			if(user==null)
				throw new UserServiceException("Incorrect Email or Password");
			return user;

	}
	
//	@Transactional
//	public void saveUserResponse(SubmitResponse submitResponse) {
//
//		userResponse.setUserId(submitResponse.getUserId());
//		userResponse.setQuestionId(submitResponse.getQuestionId());
//		userResponse.setUserAnswer(submitResponse.getUserAnswer());
//		userResponseRepository.save(userResponse);
//		Questions questionObject = questionService.findByQuestionId(userResponse.getQuestionId());
//		if(userResponse.getUserAnswer().equals(questionObject.getCorrect_option())) {
//			
//		}
//	}
	
	@Transactional
	public void savingMarks(SubmitResponse submitResponse) {
		reportCompositeKey.setUser_id(submitResponse.getUserId());
		reportCompositeKey.setCourse_id(submitResponse.getCourseId());
//		report.setReportCompKey(reportCompositeKey);
		report.setUser_id(submitResponse.getUserId());
		report.setCourse_id(submitResponse.getCourseId());
		
		if(submitResponse.getLevelId() == 1) {
			report.setLevel_1(submitResponse.getMarks());
			reportDao.save(report);
		} else if(submitResponse.getLevelId() == 2) {
			report.setLevel_2(submitResponse.getMarks());
			Reports report1 = reportDao.findById(reportCompositeKey).get();
//			Reports report1 = reportDao.findById(submitResponse.get();
			int level1 = report1.getLevel_1();
			report.setLevel_1(level1);
			reportDao.save(report);
		} else { report.setLevel_3(submitResponse.getMarks());
			Reports report1 = reportDao.findById(reportCompositeKey).get();
			int level1 = report1.getLevel_1();
			int level2 = report1.getLevel_2();
			report.setLevel_1(level1);
			report.setLevel_2(level2);
			reportDao.save(report);
		}	
	}
	
	@Transactional
	public int updatePassword(String email, String password) {
			UserDetails user = userPassRepo.findByEmail(email);
			if(user==null)
				throw new UserServiceException("The email id you entered is not registered with us!");
			user.setPassword(password);
			UserDetails updatedUser = userRepository.save(user);
			return updatedUser.getUser_id();
	}
	
	@Transactional
	public List<Reports> generateUserReports(int user_id) {
		List<Reports> list = reportRepository.findAll(user_id);
		return list;
	}
	
	
}

