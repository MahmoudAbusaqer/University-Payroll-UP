package com.projects.up.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projects.up.dao.FullTimeRepository;
import com.projects.up.dao.FulltimePaymentsRepository;
import com.projects.up.dao.LeavesRepository;
import com.projects.up.entities.FullTime;
import com.projects.up.entities.FulltimePayments;
import com.projects.up.entities.Leaves;

@Controller
@RequestMapping("/fulltime")
public class FullTimeController {
	private BufferedReader br;
	private int id;

	@Autowired
	FullTimeRepository fullTimeRepo;

	@Autowired
	LeavesRepository leavesRepo;
	
	@Autowired
	FulltimePaymentsRepository fulltimePaymentsRepo;

	@GetMapping("")
	public String displayHome(Model model) throws IOException {
		br = new BufferedReader(new FileReader("saveid.txt"));
		String st = null;
		while ((st = br.readLine()) != null) {
			id = Integer.parseInt(st);
		}
		FullTime fullTime = fullTimeRepo.findById(id).get();
		model.addAttribute("employee", fullTime);
		return "fulltime/home";
	}

	@GetMapping("/requestLeave")
	public String displayRequestLeave(Model model) {
		model.addAttribute("leave", new Leaves());
		return "fulltime/requestLeave";
	}

	@PostMapping("/requestLeave")
	public String serRequestLeave(Leaves leaves, Model model, RedirectAttributes redirectAttributes) throws IOException {
		br = new BufferedReader(new FileReader("saveid.txt"));
		String st = null;
		while ((st = br.readLine()) != null) {
			id = Integer.parseInt(st);
		}
		FullTime fullTime = fullTimeRepo.findById(id).get();
		leaves.setEmployee(fullTime);
		leavesRepo.save(leaves);
		redirectAttributes.addFlashAttribute("message", "A leave has been requested.");
		return "redirect:/fulltime/requestLeave";
	}

	@GetMapping("/paymentsLeaves")
	public String displayPaymentsLeave(Model model) throws IOException {
		br = new BufferedReader(new FileReader("saveid.txt"));
		String st = null;
		while ((st = br.readLine()) != null) {
			id = Integer.parseInt(st);
		}
		FullTime fullTime = fullTimeRepo.findById(id).get();
		
		//for Payments
		List<FulltimePayments> fulltimePayments = fulltimePaymentsRepo.findAll();
		List<FulltimePayments> fulltimePaymentsForId = new ArrayList<FulltimePayments>();
		for (int i = 0; i < fulltimePayments.size(); i++) {
			if (fulltimePayments.get(i).getEmployee().equals(fullTime)) {
				fulltimePaymentsForId.add(fulltimePayments.get(i));
			}
		}
		 model.addAttribute("FulltimePayments", fulltimePaymentsForId);
		//for leaves
		List<Leaves> leaves = leavesRepo.findAll();
		List<Leaves> leavesForId = new ArrayList<Leaves>();
		for (int i = 0; i < leaves.size(); i++) {
			if (leaves.get(i).getEmployee().equals(fullTime)) {
				leavesForId.add(leaves.get(i));
			}
		}
		 model.addAttribute("leaves", leavesForId);
		return "fulltime/paymentsLeaves";
	}

	@GetMapping("/updateInfo")
	public String displayUpdateInfo(Model model) throws IOException {
		br = new BufferedReader(new FileReader("saveid.txt"));
		String st = null;
		while ((st = br.readLine()) != null) {
			id = Integer.parseInt(st);
		}
		model.addAttribute("employee", fullTimeRepo.findById(id).get());
		return "fulltime/updateInfo";
	}

	@PostMapping("/updateInfo")
	public String setUpdateInfo(FullTime fullTime, Model model, RedirectAttributes redirectAttributes)
			throws IOException {
		br = new BufferedReader(new FileReader("saveid.txt"));
		String st = null;
		while ((st = br.readLine()) != null) {
			id = Integer.parseInt(st);
		}
		FullTime fullTime2 = fullTimeRepo.findById(id).get();
		fullTime2.setName(fullTime.getName());
		fullTime2.setEmail(fullTime.getEmail());
		fullTime2.setMethodOfPayment(fullTime.getMethodOfPayment());
		fullTimeRepo.save(fullTime2);
		redirectAttributes.addFlashAttribute("message", "Academic has been updated.");
		return "redirect:/fulltime/updateInfo";
	}
}
