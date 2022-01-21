package com.projects.up.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FullTime {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String password;
	private double salary;
	private String methodOfPayment;
	private int numberOfALLLeaves;
	private int numberOfLeavesTaken = 0;

	public FullTime() {

	}

	public FullTime(int id, String name, String email, String password, double salary, String methodOfPayment,
			int numberOfALLLeaves, int numberOfLeavesTaken) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.salary = salary;
		this.methodOfPayment = methodOfPayment;
		this.numberOfALLLeaves = numberOfALLLeaves;
		this.numberOfLeavesTaken = numberOfLeavesTaken;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getMethodOfPayment() {
		return methodOfPayment;
	}

	public void setMethodOfPayment(String methodOfPayment) {
		this.methodOfPayment = methodOfPayment;
	}

	public int getNumberOfALLLeaves() {
		return numberOfALLLeaves;
	}

	public void setNumberOfALLLeaves(int numberOfALLLeaves) {
		this.numberOfALLLeaves = numberOfALLLeaves;
	}

	public int getNumberOfLeavesTaken() {
		return numberOfLeavesTaken;
	}

	public void setNumberOfLeavesTaken(int numberOfLeavesTaken) {
		this.numberOfLeavesTaken = numberOfLeavesTaken;
	}

	@Override
	public String toString() {
		return "FullTime [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", salary="
				+ salary + ", methodOfPayment=" + methodOfPayment + ", numberOfALLLeaves=" + numberOfALLLeaves
				+ ", numberOfLeavesTaken=" + numberOfLeavesTaken + "]";
	}

}
