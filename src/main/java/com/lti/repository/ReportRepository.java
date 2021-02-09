package com.lti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lti.entity.Reports;

@Repository
public interface ReportRepository extends CrudRepository<Reports,Integer> {

	@Query("SELECT r FROM Reports r where r.user_id = ?1")
	public List<Reports> findAll(int user_id);
}
