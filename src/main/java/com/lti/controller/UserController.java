package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.RegisterStatus;
import com.lti.dto.ReportCompKey;
import com.lti.dto.Status.StatusType;
import com.lti.dto.SubmitResponse;
import com.lti.entity.LoginDetails;
import com.lti.entity.Reports;
import com.lti.entity.UserDetails;
import com.lti.exception.UserServiceException;
import com.lti.service.ReportsDaoImpl;
import com.lti.service.UserServiceImpl;

@RestController("/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private ReportsDaoImpl reportsDao;

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private MailSender mailSender;

	@PostMapping("/register")
	public RegisterStatus register(@RequestBody UserDetails user) {
		try {
			int id = userService.register(user);
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successful!");
			status.setRegisteredCustomerId(id);
			return status;
		} catch (UserServiceException e) {
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping("/login")
	public LoginStatus login(@RequestBody Login login) {
		try {
			LoginDetails user = userService.login(login.getEmail(),login.getPassword());
			System.out.println(user);
			LoginStatus status = new LoginStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Login Successful!");
			status.setUserId(user.getUser_id());
			return status;
		} catch (UserServiceException e) {
			LoginStatus status = new LoginStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
			
		}
	}

	@GetMapping("/exam_selection/{course_id}/{user_id}")
	public boolean examSelection(@PathVariable("course_id") int course_id, @PathVariable("user_id") int user_id) {

		ReportCompKey rck = new ReportCompKey(user_id, course_id);
		boolean reportExists = reportsDao.reportExists(rck);
		return reportExists;

	}
	
	@PostMapping("/submit_answers")
	public String getUserResponse(@RequestBody SubmitResponse submitResponse) {
		userService.savingMarks(submitResponse);
		if(submitResponse.getLevelId() == 1 && submitResponse.getMarks() >= 50) {
			return "Pass";
		} else if(submitResponse.getLevelId() == 2 && submitResponse.getMarks() >= 50) {
			return "Pass";
		} else if(submitResponse.getLevelId() == 3 && submitResponse.getMarks() >= 50) {
			return "Pass";
		} else {
			return "Fail";
		}
	}
	
	@PostMapping("/updatePassword")
	public RegisterStatus updatePassword(@RequestBody Login login) {
		try {
			int id = userService.updatePassword(login.getEmail(), login.getPassword());
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Update Password Succesful");
			status.setRegisteredCustomerId(id);
			return status;
		} catch (UserServiceException e) {
			RegisterStatus status = new RegisterStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@GetMapping("/forgot_password/{email}")
	public String forgotPassword(@PathVariable String email) {
		System.out.println(email);
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("codebooktests@gmail.com");
		message.setTo(email);
		message.setSubject("Password Reset");
		message.setText("Need to reset your password? No Problem!\nClick on the link below and you will be on your way!\nhttp://localhost:4200/forget_password\nIf you did not make this request please ignore this email!");
		mailSender.send(message);
		
		return "Welcome to Spring REST";
	}
	
	@GetMapping("/report/{user_id}")
	public List<Reports> getUserReports(@PathVariable String user_id) {
		List<Reports> list = userService.generateUserReports(Integer.parseInt(user_id));
		return list;
	}
}
