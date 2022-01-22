package com.projects.up.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FulltimePayments {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int FulltimePaymentsId;
	private double salary;
	private int tax;
	private Date date;

	@ManyToOne
	@JoinColumn(name = "id")
	private FullTime employee;

	
	public FulltimePayments() {
		super();
	}


	public FulltimePayments(int fulltimePaymentsId, double salary, int tax, Date date, FullTime employee) {
		super();
		FulltimePaymentsId = fulltimePaymentsId;
		this.salary = salary;
		this.tax = tax;
		this.date = date;
		this.employee = employee;
	}


	public int getFulltimePaymentsId() {
		return FulltimePaymentsId;
	}


	public void setFulltimePaymentsId(int fulltimePaymentsId) {
		FulltimePaymentsId = fulltimePaymentsId;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public int getTax() {
		return tax;
	}


	public void setTax(int tax) {
		this.tax = tax;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public FullTime getEmployee() {
		return employee;
	}


	public void setEmployee(FullTime employee) {
		this.employee = employee;
	}
	
	
}
