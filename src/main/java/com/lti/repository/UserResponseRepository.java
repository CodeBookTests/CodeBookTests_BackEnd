package com.lti.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lti.entity.UserResponse;

@Repository
public interface UserResponseRepository extends CrudRepository<UserResponse,Integer>{

}
