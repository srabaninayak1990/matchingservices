package com.walmart.matchingservices.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="CUSTOMER")
public class Customer {
	@Id
	@Column(name = "CUST_ID")
	private Integer custId;
	
	@Column(name = "CUST_EMAIL")
	private String custEmail;
	
	@Column(name = "CUST_NAME")
	private String custName;
	
	@Column(name = "CUST_ADDRESS")
	private String custAddress;

	public Integer getCustId() {
		return custId;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public String getCustName() {
		return custName;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}		
}