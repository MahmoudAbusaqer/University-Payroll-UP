package com.projects.up.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projects.up.dao.CasualRepository;
import com.projects.up.dao.FullTimeRepository;
import com.projects.up.dao.FulltimePaymentsRepository;
import com.projects.up.dao.LeavesRepository;
import com.projects.up.dao.PersonnelDepartmentRepository;
import com.projects.up.dao.TimeCardRepository;
import com.projects.up.entities.Casual;
import com.projects.up.entities.FullTime;
import com.projects.up.entities.FulltimePayments;
import com.projects.up.entities.Leaves;
import com.projects.up.entities.Mail;
import com.projects.up.entities.PersonnelDepartment;
import com.projects.up.entities.TimeCard;
import com.projects.up.services.SendMailService;
import com.projects.up.services.SendMailServiceImpl;

@Controller
@RequestMapping("/pd")
public class PersonnelDepartmentController {

//	private BufferedReader br;
//	private int id;

	@Autowired
	FullTimeRepository fullTimeRepo;

	@Autowired
	CasualRepository casualRepo;

	@Autowired
	PersonnelDepartmentRepository pdRepo;

	@Autowired
	LeavesRepository leavesRepo;

	@Autowired
	TimeCardRepository timecardRepo;
	
	@Autowired
	FulltimePaymentsRepository fulltimePaymentsRepo;

	SendMailServiceImpl sendMailServiceImpl;
	int tax = 50;

	public PersonnelDepartmentController(SendMailServiceImpl sendMailServiceImpl) {
		super();
		this.sendMailServiceImpl = sendMailServiceImpl;
	}

	@GetMapping("")
	public String displayHome(Model model) throws IOException {
//		br = new BufferedReader(new FileReader("saveid.txt"));
//		String st = null;
//		while ((st = br.readLine()) != null) {
//			id = Integer.parseInt(st);
//			System.out.println("id: " + id);
//		}
//		PersonnelDepartment pd = pdRepo.findById(id).get();;
//		model.addAttribute("employee", pd);
		List<TimeCard> timecards = timecardRepo.findAll();
		model.addAttribute("timecards", timecards);
		return "personnelDepartment/home";
	}

	@GetMapping("/casual/{id}")
	public String payTimecards(@PathVariable int id, Model model, RedirectAttributes redirectAttributes)
			throws IOException {
		TimeCard timeCard = timecardRepo.findById(id).get();
		Optional<Casual> casual = casualRepo.findById(timeCard.getEmployee().getId());

		if (casual.get().getMethodOfPayment().equals("mailed")) {
			sendMailServiceImpl.sendMail(new Mail(casual.get().getEmail(), "Your timecard has been accepted",
					"Hi, " + casual.get().getName() + ".\n Here is your Checks.\n" + "Date: "
							+ Calendar.getInstance().getTime() + "\n" + "PAY TO THE ORDER OF " + casual.get().getName()
							+ " $"
							+ ((timeCard.getHoursWorked() * casual.get().getRate()) - (timeCard.isTax() ? tax : 0))));
		} else {
			sendMailServiceImpl.sendMail(new Mail(casual.get().getEmail(), "Your timecard has been accepted",
					"Hi, " + casual.get().getName() + ".\n Your salary has been transformed into your bank account."));
		}

		timecardRepo.deleteById(id);
		redirectAttributes.addFlashAttribute("message", "The time card has been paid.");
		return "redirect:/pd";
	}

	@GetMapping("/fulltime/")
	public String payFulltime( Model model, RedirectAttributes redirectAttributes)
			throws IOException {
		System.out.println("in full time");
		Date date = Calendar.getInstance().getTime();
		List<FullTime> fullTimes = fullTimeRepo.findAll();
		for (int i = 0; i < fullTimes.size(); i++) {
			FulltimePayments fulltimePayments = new FulltimePayments();
			fulltimePayments.setSalary(fullTimes.get(i).getSalary());
			fulltimePayments.setTax(tax);
			fulltimePayments.setDate(date);
			fulltimePayments.setEmployee(fullTimes.get(i));
			fulltimePaymentsRepo.save(fulltimePayments);
			if (fullTimes.get(i).getMethodOfPayment().equals("mailed")) {
				sendMailServiceImpl.sendMail(new Mail(fullTimes.get(i).getEmail(), "Your timecard has been accepted", "Hi, "
						+ fullTimes.get(i).getName() + ".\n Here is your Checks.\n" + "Date: "
						+ Calendar.getInstance().getTime() + "\n" + "PAY TO THE ORDER OF " + fullTimes.get(i).getName()
						+ " $" + (fullTimes.get(i).getSalary() - tax)));
			}
		}
//		redirectAttributes.addFlashAttribute("error", "It is not time yet for payments.");
		redirectAttributes.addFlashAttribute("message", "All Full-Time Employees have been paid.");
		return "redirect:/pd";
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
	public String displayManageLeaves(Model model) {
		List<Leaves> leaves = leavesRepo.findAll();
		model.addAttribute("leaves", leaves);
		return "personnelDepartment/manageLeaves";
	}

	@GetMapping(path = "/manageLeaves/{id}")
	public String deleteLeaves(@PathVariable int id, RedirectAttributes redirectAttributes) {
		leavesRepo.deleteById(id);
		redirectAttributes.addFlashAttribute("message", "The leave has been deleted.");
		return "redirect:/pd/manageLeaves";
	}

	@GetMapping("/recordLeaves")
	public String displayRecordLeaves(Model model) {
		model.addAttribute("leave", new Leaves());
		return "personnelDepartment/recordLeaves";
	}

	@PostMapping("/recordLeaves")
	public String setRecordLeaves(@RequestParam int id, Model model, Leaves leaves,
			RedirectAttributes redirectAttributes) {
		int number = Integer.parseInt(leaves.getNumberOfDays());
		System.out.println("id" + id);
		System.out.println("number" + number);
		FullTime fullTime = fullTimeRepo.findById(id).orElse(null);
		if (fullTime != null) {
			System.out.println("getNumberOfLeavesTaken(): " + fullTime.getNumberOfLeavesTaken());
			int numberOfLeavesTaken = fullTime.getNumberOfLeavesTaken() + number;
			if (numberOfLeavesTaken <= fullTime.getNumberOfALLLeaves()) {
				fullTime.setNumberOfLeavesTaken(numberOfLeavesTaken);
				fullTimeRepo.save(fullTime);
				leaves.setEmployee(fullTime);
				leavesRepo.save(leaves);
				System.out.println("new fullTime.getNumberOfLeavesTaken(): " + fullTime.getNumberOfLeavesTaken());
			} else {
				System.out.println("not accepted");
			}

		}
		return "redirect:/pd/recordLeaves";
	}

}
