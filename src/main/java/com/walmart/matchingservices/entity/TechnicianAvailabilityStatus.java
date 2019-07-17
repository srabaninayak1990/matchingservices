package com.walmart.matchingservices.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="TECHNICIAN_AVAILABILITY_STATUS")
public class TechnicianAvailabilityStatus {
	@Id
	@Column(name = "TECH_ID")
	private Integer techId;
	
	@Column(name = "TECH_HOLD_STATUS")
	private String techHoldStatus;
	
	public TechnicianAvailabilityStatus() {}

	public TechnicianAvailabilityStatus(Integer techId, String techHoldStatus) {
		this.techId = techId;
		this.techHoldStatus = techHoldStatus;
	}

	public Integer getTechId() {
		return techId;
	}

	public String getTechHoldStatus() {
		return techHoldStatus;
	}

	public void setTechId(Integer techId) {
		this.techId = techId;
	}

	public void setTechHoldStatus(String techHoldStatus) {
		this.techHoldStatus = techHoldStatus;
	}
	
}