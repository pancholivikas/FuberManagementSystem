package com.cat.fms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="F_Drivers")
public class FuberDriversData {
	
	@Id
	@Column(name = "mobileNumber")
	private String mobileNumber="";
	
	@Column(name = "Name")
	private String name="";
	
	@Column(name = "isBooked")
	private int isBooked=0;
	
	@Column(name = "carTypeId")
	private String carType="";
	
	@Column(name = "lat")
	private double lat=0.0;

	@Column(name = "lng")
	private double lng=0.0;
	

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(int isBooked) {
		this.isBooked = isBooked;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLnt(double lng) {
		this.lng = lng;
	}
}
