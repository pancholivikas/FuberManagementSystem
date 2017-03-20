package com.cat.fms.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="F_RideDetails")
public class FuberRideDetailsData {

	@Id
	@Column(name="rideId")
	String rideId="";
	
	@Column(name="driverMobileNumber")
	String driverMobileNumber="";
	
	@Column(name="customerMobileNumber")
	String customerMobileNumber="";
	
	@Column(name="StartTime")
	Timestamp startTime=null;
	
	@Column(name="EndTIme")
	Timestamp endTime=null;
	
	@Column(name="charges")
	float charges = (float) 0.0;

	public String getRideId() {
		return rideId;
	}

	public void setRideId(String rideId) {
		this.rideId = rideId;
	}

	public String getDriverMobileNumber() {
		return driverMobileNumber;
	}

	public void setDriverMobileNumber(String driverMobileNumber) {
		this.driverMobileNumber = driverMobileNumber;
	}

	public String getCustomerMobileNumber() {
		return customerMobileNumber;
	}

	public void setCustomerMobileNumber(String customerMobileNumber) {
		this.customerMobileNumber = customerMobileNumber;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public float getCharges() {
		return charges;
	}

	public void setCharges(float charges) {
		this.charges = charges;
	}
	
	
}
	
	