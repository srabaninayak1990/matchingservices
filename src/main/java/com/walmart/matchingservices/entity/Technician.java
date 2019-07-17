package com.walmart.matchingservices.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name ="TECHNICIAN")
public class Technician implements Serializable{
	private static final long serialVersionUID = 8240371163427571380L;
	
	@EmbeddedId
	private TechnicianKey technicianKey;
	
	@Column(name = "TECH_NAME")
	private String techName;

	public TechnicianKey getTechnicianKey() {
		return technicianKey;
	}

	public String getTechName() {
		return techName;
	}

	public void setTechnicianKey(TechnicianKey technicianKey) {
		this.technicianKey = technicianKey;
	}

	public void setTechName(String techName) {
		this.techName = techName;
	}
		
}