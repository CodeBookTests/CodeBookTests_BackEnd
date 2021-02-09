package com.lti.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dto.ReportCompKey;
import com.lti.entity.Reports;
import com.lti.repository.ReportsDao;

@Service
public class ReportsDaoImpl {
	
	@Autowired
	ReportsDao repository;
	
	@Transactional
	public boolean reportExists(ReportCompKey reportCompKey) {
		
			Optional<Reports> r = repository.findById(reportCompKey);
			System.out.println(r);
			if (r.isPresent()) {
				return true;
			}
			else return false;
	}

}
