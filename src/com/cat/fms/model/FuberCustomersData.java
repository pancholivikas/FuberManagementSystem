package com.cat.fms.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="F_Customers")
public class FuberCustomersData {
	
	@Id
	@Column(name = "mobileNumber")
	private String mobileNumber="";
	
	@Column(name = "Name")
	private String name="";

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
}
