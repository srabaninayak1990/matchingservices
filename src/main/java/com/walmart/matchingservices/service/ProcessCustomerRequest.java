package com.walmart.matchingservices.service;

import com.walmart.matchingservices.dto.CustomerIssueRequest;

/**
 * @author srabaninayak
 *
 */

public interface ProcessCustomerRequest {
		/**
		* Process the customer request to reserve a technician and provide the reservation confirmation number to customer
		* @param CustomerIssueRequest contains issue identifier and cutsomer details
		* @return a reservation confirmation number
		*/
		String processRequest(CustomerIssueRequest customerIssueRequest);
		
		/**
		* Returns number of currently pending issues from the system
		* @return currently pending issues
		*/
		Integer numIssues();
		
}
