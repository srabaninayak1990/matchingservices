package com.walmart.matchingservices.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerIssueRequest {
	String customerEmail;
	Integer issueId;
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	public Integer getIssueId() {
		return issueId;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}
		
}
