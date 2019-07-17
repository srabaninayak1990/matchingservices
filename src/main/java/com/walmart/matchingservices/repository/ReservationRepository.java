package com.walmart.matchingservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.walmart.matchingservices.entity.Reservation;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM RESERVATION WHERE ISSUE_WORK_STATUS= ?1")
	public List<Reservation> findRecordsBasedOnStatus(String statusCode);
	
	@Query(nativeQuery = true, value = "SELECT * FROM RESERVATION WHERE RES_ID= ?1")
	public Reservation findRecordByResId(String resId);
	

}