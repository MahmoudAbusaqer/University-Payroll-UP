package com.projects.up.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pd")
public class PersonnelDepartmentController {
	@GetMapping("")
	public String displayHome() {
		return "personnelDepartment/home";
	}
	
	@GetMapping("/addEmployee")
	public String displayAddEmployee() {
		return "personnelDepartment/addEmployee";
	}
	
	@GetMapping("/deleteEmployee")
	public String displayDeleteEmployee() {
		return "personnelDepartment/deleteEmployee";
	}
	
	@GetMapping("/manageLeaves")
	public String displayManageLeaves() {
		return "personnelDepartment/manageLeaves";
	}
	
	@GetMapping("/recordLeaves")
	public String displayRecordLeaves() {
		return "personnelDepartment/recordLeaves";
	}
	
}
