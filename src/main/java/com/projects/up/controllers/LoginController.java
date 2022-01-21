package com.projects.up.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

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
	public String createAddEmployeeFullTime(@RequestParam int id, @RequestParam String password, Model model)
			throws IOException {
		FullTime fullTime = fullTimeRepo.findById(id).orElse(null);
		Casual casual = casualRepo.findById(id).orElse(null);
		PersonnelDepartment pd = pdReop.findById(id).orElse(null);
		File file = new File("saveid.txt");
		FileOutputStream fos = new FileOutputStream(file, false);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		PrintWriter pr = new PrintWriter(osw);
		pr.println(id);
		osw.close();
		pr.flush();
		pr.close();

		if (fullTime != null && fullTime.getPassword().equals(password)) {
			model.addAttribute("fullTime", fullTime);
			return "redirect:/fulltime/";
		} else if (casual != null && casual.getPassword().equals(password)) {
			model.addAttribute("casual", casual);
			return "redirect:/casual";
		} else if (pd != null && pd.getPassword().equals(password)) {
			model.addAttribute("pd", pd);
			return "redirect:/pd";
		} else {
			return "redirect:/login";
		}

	}
}
