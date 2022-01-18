package com.projects.up.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/casual")
public class CasualController {
	@GetMapping("")
	public String displayHome() {
		return "casual/home";
	}
	
	@GetMapping("/timecard")
	public String displayTimecard() {
		return "casual/timecard";
	}
	
	@GetMapping("/updateInfo")
	public String displayUpdateInfo() {
		return "casual/updateInfo";
	}
}
