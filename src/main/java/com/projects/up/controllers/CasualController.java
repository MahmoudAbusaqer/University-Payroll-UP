package com.projects.up.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projects.up.dao.CasualRepository;
import com.projects.up.dao.TimeCardRepository;
import com.projects.up.entities.Casual;
import com.projects.up.entities.TimeCard;

@Controller
@RequestMapping("/casual")
public class CasualController {
	
	private BufferedReader br;
	private int id;
	
	@Autowired
	CasualRepository casualRepo;
	
	@Autowired
	TimeCardRepository timeCardRepo;
	
	@GetMapping("")
	public String displayHome(Model model) throws IOException {
		br = new BufferedReader(new FileReader("saveid.txt"));
		String st = null;
		while ((st = br.readLine()) != null) {
			id = Integer.parseInt(st);
		}
		Casual casual = casualRepo.findById(id).get();
		model.addAttribute("employee", casual);
		return "casual/home";
	}
	
	@GetMapping("/timecard")
	public String displayTimecard(Model model) {
		model.addAttribute("timecard", new TimeCard());
		return "casual/timecard";
	}
	
	@PostMapping("/timecard")
	public String setTimecard(TimeCard timeCard, Model model, RedirectAttributes redirectAttributes) throws IOException{
		br = new BufferedReader(new FileReader("saveid.txt"));
		String st = null;
		while ((st = br.readLine()) != null) {
			id = Integer.parseInt(st);
		}
		Casual casual = casualRepo.findById(id).get();
		timeCard.setEmployee(casual);
		timeCardRepo.save(timeCard);
		redirectAttributes.addFlashAttribute("message", "The time card has been sent.");
		return "redirect:/casual/timecard";
	}
	
	@GetMapping("/updateInfo")
	public String displayUpdateInfo(Model model) throws IOException {
		br = new BufferedReader(new FileReader("saveid.txt"));
		String st = null;
		while ((st = br.readLine()) != null) {
			id = Integer.parseInt(st);
		}
		model.addAttribute("employee", casualRepo.findById(id).get());
		return "casual/updateInfo";
	}
	
	@PostMapping("/updateInfo")
	public String setUpdateInfo(Casual casual, Model model, RedirectAttributes redirectAttributes) throws IOException {
		br = new BufferedReader(new FileReader("saveid.txt"));
		String st = null;
		while ((st = br.readLine()) != null) {
			id = Integer.parseInt(st);
		}
		Casual casual2 = casualRepo.findById(id).get();
		casual2.setName(casual.getName());
		casual2.setEmail(casual.getEmail());
		casual2.setMethodOfPayment(casual.getMethodOfPayment());
		casualRepo.save(casual2);
		redirectAttributes.addFlashAttribute("message", "Academic has been updated.");
		return "redirect:/casual/updateInfo";
	}
}
