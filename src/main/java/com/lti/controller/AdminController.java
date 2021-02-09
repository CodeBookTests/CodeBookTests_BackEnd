package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AdminSearchStudent;
import com.lti.dto.Login;
import com.lti.dto.LoginStatus;
import com.lti.dto.Status.StatusType;
import com.lti.entity.AdminLoginDetail;
import com.lti.entity.Questions;
import com.lti.entity.UserDetails;
import com.lti.exception.UserServiceException;
import com.lti.repository.UserRepository;
import com.lti.service.AdminService;
import com.lti.service.QuestionServiceImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private QuestionServiceImpl qs;
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value="/add_question",method=RequestMethod.POST)
	//@PostMapping("/add_question")
	public Questions insert(@RequestBody Questions question) {
		return adminService.insert(question);
	}

	@DeleteMapping("/remove_question/{id}")
	public void delete(@PathVariable int id) {
//		int iD = Integer.parseInt(id);
//		adminService.delete(iD);
		adminService.delete(id);
	}

	@GetMapping("/questions/{course_id}/{level_id}")
	public List<Questions> questionList(@PathVariable("course_id") int course_id,@PathVariable("level_id") int level_id){
		return qs.find(course_id, level_id);
	}
	
	@PostMapping("/admin_login") 
	public LoginStatus adminLogin(@RequestBody Login login) {
		try {
			AdminLoginDetail admin = adminService.login(login.getEmail(),login.getPassword());
			System.out.println(admin);
			LoginStatus status = new LoginStatus();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Login Successful!");
			status.setUserId(admin.getAdminId());
			return status;
		} catch (UserServiceException e) {
			LoginStatus status = new LoginStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	@PostMapping("/search_students")
	public List<UserDetails> searchStudents(@RequestBody AdminSearchStudent adminSearchStudent) {
		List<UserDetails> list = adminService.searchStudents(adminSearchStudent);
		return list;
//		return adminService.searchStudents(adminSearchStudent);
//		return new ResponseEntity<List<UserDetails>>(userRepo.findAllUsers(), HttpStatus.OK);
	}
	

	
}
