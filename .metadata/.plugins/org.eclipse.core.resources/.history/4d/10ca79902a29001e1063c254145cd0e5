package com.accurate.model.invoice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class CustomerDO {
	@Column(name="Gst_No")
	String gstNo;

	@Column(name="address1")
	String address1;

	@Column(name="address2")
	String address2;

	@Column(name="city")
	String city;

	@Column(name="pincode")
	Integer pincode;

	@Column(name="state")
	String state;

	@Column(name="country")
	String country;

	@Column(name="email")
	String email;

	@Column(name="contact_no")
	String contactNo;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Customer_Id")
	Integer customerId;

	@Column(name = "Customer_Name")
	String customerName;
	
	

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String toString() {
		return "CustomerDO [gstNo=" + gstNo + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city
				+ ", pincode=" + pincode + ", state=" + state + ", country=" + country + ", email=" + email
				+ ", contactNo=" + contactNo + ", customerId=" + customerId + ", customerName=" + customerName + "]";
	}
	
	
}
