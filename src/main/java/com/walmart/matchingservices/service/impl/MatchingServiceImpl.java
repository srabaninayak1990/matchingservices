package com.walmart.matchingservices.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.matchingservices.constants.Constants;
import com.walmart.matchingservices.entity.Criteria;
import com.walmart.matchingservices.entity.Reservation;
import com.walmart.matchingservices.entity.Technician;
import com.walmart.matchingservices.entity.TechnicianAvailabilityStatus;
import com.walmart.matchingservices.exception.MatchingServiceException;
import com.walmart.matchingservices.repository.ReservationRepository;
import com.walmart.matchingservices.repository.TechnicianAvailabilityStatusRepository;
import com.walmart.matchingservices.repository.TechnicianRepository;
import com.walmart.matchingservices.service.MatchingService;
import com.walmart.matchingservices.utils.ReservationCodeGenerator;

@Service
public class MatchingServiceImpl implements  MatchingService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MatchingServiceImpl.class);
	
	@Autowired
	private TechnicianRepository technicianRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	private TechnicianAvailabilityStatusRepository technicianAvailabilityStatusRepository;
	
	@Override
	public int numIssues (){
		
		return 0;
	}
	
	@Override
	public Technician findAndAssignTecnician(Criteria criteria, String customerEmail){
		List<Technician> capableTechnicianList = null;
		List<TechnicianAvailabilityStatus> techniciansAvailabilityStatus= null;
		Technician assignedTechnician = null;
		Integer techId;
		
		try{
			//Find all the technicians who has capability to serve the customer issue based on provided issueId criteria 
			capableTechnicianList = technicianRepository.findTechByCapabilityId(criteria.getIssueId());
			
			//Retrieve unique techIds from above capable technicians list
			Set<Integer> techIds = capableTechnicianList.stream().map(techRecord -> techRecord.getTechnicianKey().getTechId()).collect(Collectors.toSet()); 
			
			//Identify all technicians from above capable of technicians who are currently available to serve the request
			techniciansAvailabilityStatus = technicianAvailabilityStatusRepository.findAvailableTechListByTechIds(techIds,Constants.TECHNICIAN_AVAILABILITY_CODE);
			
			if(null != techniciansAvailabilityStatus && !techniciansAvailabilityStatus.isEmpty()){
				//Assign the customer issue to first available technician
				techId = techniciansAvailabilityStatus.get(0).getTechId();
				
				//Mark the above technician status to Not available (Hold) until current work status is completed
				technicianAvailabilityStatusRepository.save(new TechnicianAvailabilityStatus(techId,Constants.TECHNICIAN_NOT_AVAILABILITY_CODE));
				
				//Map corresponding technician object for above assigned techId
				assignedTechnician = capableTechnicianList.stream().filter(techRecord -> techId.equals(techRecord.getTechnicianKey().getTechId())).findAny().orElse(null);
			}
		}catch(Exception ex){
			String errorMsg = "Exception occured in find and assign technician propcess";
			LOGGER.error(errorMsg);
			throw new MatchingServiceException(errorMsg);
		}
		
		return assignedTechnician;
	}
	
	@Override
	public String reserveTechnician(int issueId, String customerEmail, int techId){
		Reservation reservation = new Reservation();
		String reservationCode = null;
		
		try{
			//Generate the confirmation number
			reservationCode = ReservationCodeGenerator.generateCode();
			reservation.setReservationConfirmationId(reservationCode);
			reservation.setRequestDate(new Timestamp(System.currentTimeMillis()));
			reservation.setCustEmail(customerEmail);
			reservation.setIssueId(issueId);
			reservation.setTechId(techId);
			reservation.setIssueWorkStatus(Constants.ISSUE_WORK_PENDING_STATUS);
			
			//Save the reservation confirmation and other details into DB table
			reservationRepository.save(reservation);
			
			String resMsg = new StringBuilder("Confirmation number for email: ")
					.append(customerEmail)
					.append(", TechnicianId: ")
					.append(techId)
					.append(", confirmationCode: ")
					.append(reservationCode)
					.toString();
			
			LOGGER.info(resMsg);
		}catch(Exception ex){
			String errorMsg = "Exception occured in reservation confirmation process";
			LOGGER.error(errorMsg);
			throw new MatchingServiceException(errorMsg);
		}
		
		return reservationCode;
	}
	
}
