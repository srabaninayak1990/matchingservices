package com.walmart.matchingservices.service;

import com.walmart.matchingservices.dto.CustomerIssueRequest;

public interface ProcessCustomerRequest {
	
		String processRequest(CustomerIssueRequest customerIssueRequest);
		
		Integer numIssues();
		
}
