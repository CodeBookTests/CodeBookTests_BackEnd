package com.lti.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.lti.entity.Questions;

@org.springframework.stereotype.Repository
public interface QuestionService extends Repository<Questions,Integer>{
	
	public List<Questions> findByCourseIdAndLevelId(int course_id, int level_id);
	
//	public Questions findByQuestionId(int question_id);

}
