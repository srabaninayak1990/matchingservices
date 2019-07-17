package com.walmart.matchingservices.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TechnicianKey implements Serializable{
	
	private static final long serialVersionUID = 8457918576818191971L;
	
	@Column(name = "TECH_ID", nullable = false)
	private Integer techId;
	
	@Column(name = "TECH_CAPABILITY_ID", nullable = false)
	private Integer techCapabilityId;

	public Integer getTechId() {
		return techId;
	}

	public Integer getTechCapabilityId() {
		return techCapabilityId;
	}

	public void setTechId(Integer techId) {
		this.techId = techId;
	}

	public void setTechCapabilityId(Integer techCapabilityId) {
		this.techCapabilityId = techCapabilityId;
	}
				
}