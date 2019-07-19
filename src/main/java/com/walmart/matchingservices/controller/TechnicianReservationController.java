package com.walmart.matchingservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.matchingservices.dto.CustomerIssueRequest;
import com.walmart.matchingservices.service.ProcessCustomerRequest;


@RestController
@RequestMapping("/matching_service")
public class TechnicianReservationController {
	
	@Autowired
	private ProcessCustomerRequest processCustomerRequest;
	
    @PostMapping(path="/tech_confirmation")
    public ResponseEntity<String> reserveTechnician(@RequestBody CustomerIssueRequest customerIssueRequest) {
    	String reservationConfirmation;
    	try{
    		reservationConfirmation = processCustomerRequest.processRequest(customerIssueRequest);
    	}catch(Exception ex){
    			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	
    	String response = "Your reservation confirmation number : " + reservationConfirmation;
    	
        return new ResponseEntity<>(response, HttpStatus.OK);  
    }
    
    @GetMapping(path="/num_issues")
    public ResponseEntity<String> getNumIssues(){
    	Integer numIssues = 0;
    	try{
    		numIssues = processCustomerRequest.numIssues();
    	}catch(Exception ex){
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	
	String response = (numIssues > 0 ? "Pending issues : " + numIssues.toString() : "No pending issues");
	
    return new ResponseEntity<>(response , HttpStatus.OK);  
    }  

}