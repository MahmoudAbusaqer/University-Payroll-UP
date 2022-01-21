package com.projects.up.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Leaves {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int leaveId;
	private String numberOfDays;
	private String type;
	private String reason;

	@ManyToOne
	@JoinColumn(name = "id")
	private FullTime employee;

	public Leaves() {
		super();
	}

	public Leaves(int leaveId, String numberOfDays, String type, String reason, FullTime employee) {
		super();
		this.leaveId = leaveId;
		this.numberOfDays = numberOfDays;
		this.type = type;
		this.reason = reason;
		this.employee = employee;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public String getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(String numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public FullTime getEmployee() {
		return employee;
	}

	public void setEmployee(FullTime employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Leaves [leaveId=" + leaveId + ", numberOfDays=" + numberOfDays + ", type=" + type + ", reason=" + reason
				+ ", employee=" + employee + "]";
	}

}
