package com.manage.CitySafety.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FactoryLabour {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	private String factoryLabourName;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFactoryLabourName() {
		return factoryLabourName;
	}
	public void setFactoryLabourName(String factoryLabourName) {
		this.factoryLabourName = factoryLabourName;
	}
	

}
