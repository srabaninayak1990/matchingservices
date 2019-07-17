package com.walmart.matchingservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.walmart.matchingservices.entity.Technician;


@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Integer> {
	@Query(nativeQuery = true, value = "SELECT * FROM TECHNICIAN WHERE  TECH_CAPABILITY_ID= ?1")
	public List<Technician> findTechByCapabilityId(Integer techCapabilityId);
}