package com.lti.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lti.entity.Questions;

@Repository
public interface QuestionsRepository extends CrudRepository<Questions,Integer>{

}