package com.walmart.matchingservices.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="RESERVATION")
public class Reservation {
	@Id
	@Column(name = "RES_ID")
	private String reservationConfirmationId;
	
	@Column(name = "TECH_ID")
	private Integer techId;
	
	@Column(name = "ISSUE_ID")
	private Integer issueId;
	
	@Column(name = "CUST_EMAIL")
	private String custEmail;
	
	@Column(name = "ISSUE_WORK_STATUS")
	private String issueWorkStatus;
	
	@Column(name = "REQUEST_DATE")
	private Timestamp requestDate;
	
	@Column(name = "COMPLETION_DATE")
	private Timestamp completionDate;

	public String getReservationConfirmationId() {
		return reservationConfirmationId;
	}

	public Integer getTechId() {
		return techId;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public String getIssueWorkStatus() {
		return issueWorkStatus;
	}

	public Timestamp getRequestDate() {
		return requestDate;
	}

	public Timestamp getCompletionDate() {
		return completionDate;
	}

	public void setReservationConfirmationId(String reservationConfirmationId) {
		this.reservationConfirmationId = reservationConfirmationId;
	}

	public void setTechId(Integer techId) {
		this.techId = techId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public void setIssueWorkStatus(String issueWorkStatus) {
		this.issueWorkStatus = issueWorkStatus;
	}

	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	public void setCompletionDate(Timestamp completionDate) {
		this.completionDate = completionDate;
	}					
}