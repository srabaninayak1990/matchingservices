package com.walmart.matchingservices.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="CRITERIA")
public class Criteria {
	@Id
	@Column(name = "ISSUE_ID")
	private Integer issueId;
	
	@Column(name = "ISSUE_TYPE")
	private String issueType;
	
	public Criteria() {}

	public Criteria(Integer issueId, String issueType) {
		this.issueId = issueId;
		this.issueType = issueType;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}	
}