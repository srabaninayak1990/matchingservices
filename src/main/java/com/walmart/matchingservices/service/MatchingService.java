package com.walmart.matchingservices.service;

import com.walmart.matchingservices.entity.Criteria;
import com.walmart.matchingservices.entity.Technician;

/**
 * @author srabaninayak
 *
 */

public interface MatchingService {
	
	/**
	* The number of pending issues ready to be assigned 
	* @return the number of pending issues 
	*/
	int numIssues ();
	
	/**
	* Find and assign a matching technician based on criteria and technician ability
	* @param Criteria needed to be matched
	* @param customerEmail unique identifier for the customer 
	* @return a Technician object with tech details
	*/
	Technician findAndAssignTecnician(Criteria criteria, String customerEmail);
	
	/**
	* Assigned Issue
	* @param issueId the issues identifier
	* @param customerEmail the email address of the customer to which the reservation is assigned
	* @param techId the identifier of the technician
	* @return a reservation confirmation code
	*/
	String reserveTechnician(int issueId, String customerEmail, int techId);		
}
