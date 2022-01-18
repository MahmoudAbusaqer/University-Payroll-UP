package com.projects.up.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fulltime")
public class FullTimeController {
	@GetMapping("")
	public String displayHome() {
		return "fulltime/home";
	}
	
	@GetMapping("/requestLeave")
	public String displayRequestLeave() {
		return "fulltime/requestLeave";
	}
	
	@GetMapping("/paymentsLeaves")
	public String displayPaymentsLeave() {
		return "fulltime/paymentsLeaves";
	}
	
	@GetMapping("/updateInfo")
	public String displayUpdateInfo() {
		return "fulltime/updateInfo";
	}
}
