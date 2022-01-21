package com.projects.up.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TimeCard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int timeCardId;
	private int hoursWorked;
	private boolean tax;

	@ManyToOne
	@JoinColumn(name = "id")
	private Casual employee;

	public TimeCard() {
		super();
	}

	public TimeCard(int timeCardId, int hoursWorked, boolean tax, Casual employee) {
		super();
		this.timeCardId = timeCardId;
		this.hoursWorked = hoursWorked;
		this.tax = tax;
		this.employee = employee;
	}

	public int getTimeCardId() {
		return timeCardId;
	}

	public void setTimeCardId(int timeCardId) {
		this.timeCardId = timeCardId;
	}

	public int getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public boolean isTax() {
		return tax;
	}

	public void setTax(boolean tax) {
		this.tax = tax;
	}

	public Casual getEmployee() {
		return employee;
	}

	public void setEmployee(Casual employee) {
		this.employee = employee;
	}

}
