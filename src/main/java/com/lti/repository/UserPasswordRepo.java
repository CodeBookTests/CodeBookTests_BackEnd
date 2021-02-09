package com.lti.repository;

import com.lti.entity.UserDetails;
import org.springframework.data.repository.Repository;

public interface UserPasswordRepo extends Repository<UserDetails,Integer>{

		public UserDetails findByEmail(String email);
}
