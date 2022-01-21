package com.projects.up.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projects.up.dao.CasualRepository;
import com.projects.up.dao.FullTimeRepository;
import com.projects.up.dao.PersonnelDepartmentRepository;
import com.projects.up.entities.Casual;
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

	@GetMapping("/")
	public String displayHome(Model model) {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String displayLogin(Model model) {
		return "login";
	}

	@PostMapping("/login")
	public String createAddEmployeeFullTime(@RequestParam int id, @RequestParam String password, Model model) {
		FullTime fullTime = fullTimeRepo.findById(id).orElse(null);
		Casual casual = casualRepo.findById(id).orElse(null);
		PersonnelDepartment pd = pdReop.findById(id).orElse(null);
		if (fullTime != null && fullTime.getPassword().equals(password)) {
			System.out.println("state 1");
			model.addAttribute("fullTime", fullTime);
			System.out.println(fullTime.getName());
			return "redirect:/fulltime";
		} else if (casual != null && casual.getPassword().equals(password)) {
			System.out.println("state 2");
			model.addAttribute("casual", casual);
			System.out.println(casual.getName());
			return "redirect:/casual";
		} else if (pd != null && pd.getPassword().equals(password)) {
			System.out.println("state 3");
			model.addAttribute("pd", pd);
			System.out.println(pd.getName());
			return "redirect:/pd";
		} else {
			System.out.println("state 4");
			return "redirect:/login";
		}

	}
}
