package com.projects.up.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.up.dao.CasualRepository;
import com.projects.up.dao.FullTimeRepository;
import com.projects.up.entities.Casual;
import com.projects.up.entities.FullTime;

@Controller
@RequestMapping("/pd")
public class PersonnelDepartmentController {

	@Autowired
	FullTimeRepository fullTimeRepo;

	@Autowired
	CasualRepository casualRepo;

	@GetMapping("")
	public String displayHome() {
		return "personnelDepartment/home";
	}

	@GetMapping("/addEmployee")
	public String displayAddEmployee(Model model) {
		model.addAttribute("fullTimeEmployee", new FullTime());
		model.addAttribute("casualEmployee", new Casual());
		return "personnelDepartment/addEmployee";
	}

	@PostMapping("/addEmployee/saveFull")
	public String createAddEmployeeFullTime(FullTime fullTime, Model model) {
		fullTimeRepo.save(fullTime);
		// this redirect is to prevent duplicate submissions
		return "redirect:/pd/addEmployee";
	}

	@PostMapping("/addEmployee/saveCasual")
	public String createAddEmployeeCasual(Casual casual, Model model) {
		casualRepo.save(casual);
		return "redirect:/pd/addEmployee";
	}

	@GetMapping("/deleteEmployee")
	public String displayDeleteEmployee(Model model) {
		List<FullTime> fullTimes = fullTimeRepo.findAll();
		List<Casual> casuals = casualRepo.findAll();
		model.addAttribute("fullTimeEmployees", fullTimes);

		model.addAttribute("casualEmployees", casuals);

		return "personnelDepartment/deleteEmployee";
	}

	@GetMapping(path = "/deleteEmployee/deletefull/{id}")
	public String deleteEmployeeFull(@PathVariable int id) {
		fullTimeRepo.deleteById(id);
		return "redirect:/pd/deleteEmployee";
	}

	@GetMapping(path = "/deleteEmployee/deletecasual/{id}")
	public String deleteEmployeeCasual(@PathVariable int id) {
		casualRepo.deleteById(id);
		return "redirect:/pd/deleteEmployee";
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
