package com.walmart.matchingservices.controller.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.walmart.matchingservices.controller.TechnicianReservationController;
import com.walmart.matchingservices.dto.CustomerIssueRequest;
import com.walmart.matchingservices.exception.MatchingServiceException;
import com.walmart.matchingservices.service.ProcessCustomerRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TechnicianReservationControllerTest {

    @InjectMocks
    private TechnicianReservationController technicianReservationController;

    @Mock
    private ProcessCustomerRequest processCustomerRequest;
    
    CustomerIssueRequest customerIssueRequest;

    @org.junit.Before
    public void setUp() {
    	customerIssueRequest = getReqObject();
    }

    @org.junit.After
    public void tearDown() {
    }

    @Test
    public void testReserveTechnicianReturns200() {
    	String resId = "";
    	when(processCustomerRequest.processRequest(customerIssueRequest)).thenReturn(resId);
        ResponseEntity<String> response = technicianReservationController.reserveTechnician(customerIssueRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void testReserveTechnicianReturns500() {
    	when(processCustomerRequest.processRequest(customerIssueRequest)).thenThrow(MatchingServiceException.class);
        ResponseEntity<String> response = technicianReservationController.reserveTechnician(customerIssueRequest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    
    @Test
    public void verifyProcessRequestMethodCalled() throws  MatchingServiceException{
    	technicianReservationController.reserveTechnician(customerIssueRequest);
        verify(processCustomerRequest).processRequest(customerIssueRequest);
    }

    @Test
    public void testReponseObject() {
    	String resId = "12345";
    	String serviceResponse = "Your reservation confirmation number : " + resId;
    	when(processCustomerRequest.processRequest(customerIssueRequest)).thenReturn(resId);
    	ResponseEntity<String> response = technicianReservationController.reserveTechnician(customerIssueRequest);        
        assertEquals(serviceResponse, response.getBody());
    }
    
    @Test
    public void testGetNumIssuesReturns200() {
    	when(processCustomerRequest.numIssues()).thenReturn(2);
        ResponseEntity<String> response = technicianReservationController.getNumIssues();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void testGetNumIssuesReturns500() {
    	when(processCustomerRequest.numIssues()).thenThrow(MatchingServiceException.class);
        ResponseEntity<String> response = technicianReservationController.getNumIssues();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    
    @Test
    public void verifyNumIssuesMethodCalled() throws  MatchingServiceException{
    	technicianReservationController.getNumIssues();
        verify(processCustomerRequest).numIssues();
    }

    @Test
    public void testValidGetNumIssuesReponseObject() {
    	Integer numIssues= 2;
    	String serviceResponse = "Pending issues : " + numIssues.toString();
    	when(processCustomerRequest.numIssues()).thenReturn(numIssues);
        ResponseEntity<String> response = technicianReservationController.getNumIssues();       
        assertEquals(serviceResponse, response.getBody());
    }
    
    @Test
    public void testNoPendingNumIssuesReponseObject() {
    	Integer numIssues= 0;
    	String serviceResponse = "No pending issues";
    	when(processCustomerRequest.numIssues()).thenReturn(numIssues);
        ResponseEntity<String> response = technicianReservationController.getNumIssues();       
        assertEquals(serviceResponse, response.getBody());
    }
    
    private CustomerIssueRequest getReqObject() {
        return new CustomerIssueRequest();
    }
}