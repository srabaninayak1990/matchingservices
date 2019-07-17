package com.walmart.matchingservices.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.walmart.matchingservices.entity.TechnicianAvailabilityStatus;

@Repository
public interface TechnicianAvailabilityStatusRepository extends JpaRepository<TechnicianAvailabilityStatus, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT TECH_ID, TECH_HOLD_STATUS FROM TECHNICIAN_AVAILABILITY_STATUS WHERE  TECH_ID IN (:techIds) AND TECH_HOLD_STATUS= :statusCode ")
	public List<TechnicianAvailabilityStatus> findAvailableTechListByTechIds(@Param("techIds") Set<Integer> techIds, @Param("statusCode") String statusCode);

}