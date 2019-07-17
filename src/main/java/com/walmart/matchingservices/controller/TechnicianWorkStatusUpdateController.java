package com.walmart.matchingservices.controller;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.matchingservices.constants.Constants;
import com.walmart.matchingservices.entity.Reservation;
import com.walmart.matchingservices.entity.TechnicianAvailabilityStatus;
import com.walmart.matchingservices.exception.MatchingServiceException;
import com.walmart.matchingservices.repository.ReservationRepository;
import com.walmart.matchingservices.repository.TechnicianAvailabilityStatusRepository;



@RestController
@RequestMapping("/update_status")
public class TechnicianWorkStatusUpdateController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TechnicianWorkStatusUpdateController.class);
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private TechnicianAvailabilityStatusRepository technicianAvailabilityStatusRepository;
	 
    @GetMapping
    public ResponseEntity<String> updateWorkStatus(@RequestParam("resId") String resId, @RequestParam("status") String status, @RequestParam("techId") Integer techId ){
    	Reservation reservation  = null;
    	
    	try{
    		//Retrieve existing reservation record for above reservation Id
    		reservation = reservationRepository.findRecordByResId(resId);
    		if(null != reservation){
    			//Update the existing reservation record with latest work status
    			reservation.setIssueWorkStatus(status);
    	    	reservation.setCompletionDate(new Timestamp(System.currentTimeMillis()));
    	    	reservationRepository.save(reservation);
    		}else{
    			String errorMsg = String.join(": ", "Invalid Reservation Confirmation ID ", resId);
    			LOGGER.error(errorMsg);
    			throw new MatchingServiceException(errorMsg);
    		}
    		
    		//Mark the technician status to available
			technicianAvailabilityStatusRepository.save(new TechnicianAvailabilityStatus(techId,Constants.TECHNICIAN_AVAILABILITY_CODE));			
    	}catch(Exception ex){
    		LOGGER.error("Exception occured while updating the work status details");
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    	}
	
    return new ResponseEntity<>("Sucessfully updated the work status", HttpStatus.OK);  
    }  

}