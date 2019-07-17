package com.walmart.matchingservices.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.matchingservices.constants.Constants;
import com.walmart.matchingservices.dto.CustomerIssueRequest;
import com.walmart.matchingservices.entity.Criteria;
import com.walmart.matchingservices.entity.Reservation;
import com.walmart.matchingservices.entity.Technician;
import com.walmart.matchingservices.exception.MatchingServiceException;
import com.walmart.matchingservices.repository.CriteriaRepository;
import com.walmart.matchingservices.repository.ReservationRepository;
import com.walmart.matchingservices.service.MatchingService;
import com.walmart.matchingservices.service.ProcessCustomerRequest;



@Service
public class ProcessCustomerRequestImpl implements ProcessCustomerRequest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessCustomerRequestImpl.class);
	
	@Autowired
	private MatchingService matchingService;
	
	@Autowired
	private CriteriaRepository criteriaRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public String processRequest(CustomerIssueRequest customerIssueRequest){

		String response = null;
		Technician technician = null;
		
		//Set Criteria object based on customer issueId
		Criteria criteria = criteriaRepository.findByIssueId(customerIssueRequest.getIssueId());
		
		if(null != criteria){
			LOGGER.info("{} is a valid issueID",customerIssueRequest.getIssueId());
			
			//Identify a technician who is capable and available to server the customer issue
			technician = matchingService.findAndAssignTecnician(criteria, customerIssueRequest.getCustomerEmail());
			
			//Reserve above identified technician and return a confirmation number
			if(null != technician){
				response = matchingService.reserveTechnician(customerIssueRequest.getIssueId(), customerIssueRequest.getCustomerEmail(), technician.getTechnicianKey().getTechId());
			}else{
				String errorMsg = String.join(": ", "None of the technicians are currently available or capable to handle this specific customer issue ID ", String.valueOf(customerIssueRequest.getIssueId()));
				LOGGER.error(errorMsg);
				throw new MatchingServiceException(errorMsg);
			}
		}else{
			String errorMsg = String.join(": ", "Invalid Issue ID ", String.valueOf(customerIssueRequest.getIssueId()));
			LOGGER.error(errorMsg);
			throw new MatchingServiceException(errorMsg);
		}
		
		return response;
	}
	
	@Override
	public Integer numIssues(){
		List<Reservation> pendingIssueRecords= new ArrayList<>();
		try{
			pendingIssueRecords = reservationRepository.findRecordsBasedOnStatus(Constants.ISSUE_WORK_PENDING_STATUS);
		}catch(Exception ex){
			String errorMsg = "Exception occured while retrieving the number of pending issues";
			LOGGER.error(errorMsg);
			throw new MatchingServiceException(errorMsg);
		}
		return pendingIssueRecords.size();
	}
}
