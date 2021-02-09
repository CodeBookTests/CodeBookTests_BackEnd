package com.lti.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Questions;
import com.lti.repository.QuestionService;

@Service
public class QuestionServiceImpl {
	@Autowired
	public QuestionService questionService;
	
	@Transactional
	public List<Questions> find(int course_id, int level_id) {
		List<Questions> q = questionService.findByCourseIdAndLevelId(course_id, level_id);
		return q;
	}

}
