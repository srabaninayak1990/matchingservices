package com.walmart.matchingservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.walmart.matchingservices.entity.Criteria;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT ISSUE_ID, ISSUE_TYPE FROM CRITERIA WHERE  ISSUE_ID= ?1")
	public Criteria findByIssueId(Integer issueId);

}