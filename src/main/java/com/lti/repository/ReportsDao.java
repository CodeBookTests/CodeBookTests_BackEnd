package com.lti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.dto.ReportCompKey;
import com.lti.entity.Reports;

@Repository
public interface ReportsDao extends JpaRepository<Reports, ReportCompKey> {
	
//	public Reports findByCourseIdAndLevel1(int course_id, int level_1);
//	
//	public Reports findByCourseIdAndLevel2(int course_id, int level_2);
//	
//	public Reports findByCourseIdAndLevel3(int course_id, int level_3);
	
	

}
