package com.projects.up.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.up.dao.CasualRepository;
import com.projects.up.dao.FullTimeRepository;
import com.projects.up.dao.PersonnelDepartmentRepository;
import com.projects.up.entities.FullTime;
import com.projects.up.entities.PersonnelDepartment;

@Controller
@RequestMapping("/")
public class LoginController {
	@Autowired
	FullTimeRepository fullTimeRepo;

	@Autowired
	CasualRepository casualRepo;

	@Autowired
	PersonnelDepartmentRepository pdReop;

	@GetMapping("/login")
	public String displayHome(Model model) {
//		model.addAttribute("employee", new PersonnelDepartment());
		model.addAttribute("employee", new FullTime());
		return "login";
	}

	@GetMapping("/login/{id}")
	public String createAddEmployeeFullTime(@PathVariable int id) {
		System.out.println("0");
		fullTimeRepo.findById(id);
//		casualRepo.findById(id);
//		pdReop.findById(id);
//		if (fullTimeRepo != null) {
//		    System.out.println("1");
			return "redirect:/fulltime";
//		}
//		else
//
//		if (casualRepo != null) {
//			System.out.println("2");
//			return "redirect:/casual";
//		}
//		else
//		if (pdReop != null) {
//			System.out.println("3");
//			return "redirect:/pd";
//		}else {
//			System.out.println("4");
//			return "redirect:/login";
//		}
		
	}
}











