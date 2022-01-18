package com.projects.up.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class FullTime {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private String name;
	private String email;
	private String password;
	private double salary;
	private String methodOfPayment;

	public FullTime() {

	}

	public FullTime(int id, String name, String email, String password, double salary, String methodOfPayment) {
		super();
		Id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.salary = salary;
		this.methodOfPayment = methodOfPayment;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

}
