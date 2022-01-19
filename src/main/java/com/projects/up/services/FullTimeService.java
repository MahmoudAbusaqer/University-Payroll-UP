package com.projects.up.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.up.dao.FullTimeRepository;

@Service
public class FullTimeService {
	//Field Injection
	@Autowired
	FullTimeRepository fullTimeRepo;
//
//Constructor Injection
public FullTimeService(FullTimeRepository fullTimeRepo) {
	super();
	this.fullTimeRepo = fullTimeRepo;
}
//
////Setter Injection
//@Autowired
//public void setFullTimeRepoo(EmployeeRepository empRepo) {
//	this.empRepo = empRepo;
//}
}
