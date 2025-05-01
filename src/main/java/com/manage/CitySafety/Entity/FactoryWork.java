package com.manage.CitySafety.Entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FactoryWork {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate date;
	private String labourName;
	private String importMetarial;
	private String exportMaterial;
	private double transportCost;
	private double securityMoney;
	private double advanceMoney;
	private double mealCost;
	private String carRent;
	private String deliveryAddress;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	
	
	public String getLabourName() {
		return labourName;
	}
	public void setLabourName(String labourName) {
		this.labourName = labourName;
	}
	public double getMealCost() {
		return mealCost;
	}
	public void setMealCost(double mealCost) {
		this.mealCost = mealCost;
	}
	public String getCarRent() {
		return carRent;
	}
	public void setCarRent(String carRent) {
		this.carRent = carRent;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getExportMaterial() {
		return exportMaterial;
	}
	public void setExportMaterial(String exportMaterial) {
		this.exportMaterial = exportMaterial;
	}
	public String getImportMetarial() {
		return importMetarial;
	}
	public void setImportMetarial(String importMetarial) {
		this.importMetarial = importMetarial;
	}
	public double getTransportCost() {
		return transportCost;
	}
	public void setTransportCost(double transportCost) {
		this.transportCost = transportCost;
	}
	public double getSecurityMoney() {
		return securityMoney;
	}
	public void setSecurityMoney(double securityMoney) {
		this.securityMoney = securityMoney;
	}
	public double getAdvanceMoney() {
		return advanceMoney;
	}
	public void setAdvanceMoney(double advanceMoney) {
		this.advanceMoney = advanceMoney;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	
	

}
