package com.ecb.bean;

import java.util.Date;

public class Customer {
	
	private String customerId;
	private String fullName;
	private String email;
	private String password;
	private String phoneNumber;
	private Date birthday;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(String customerId, String fullName, String email, String password, String phoneNumber,
			Date birthday) {
		super();
		this.customerId = customerId;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean search(Customer customer) {
		if (customer== null) 
			return true;
		if (this.fullName != null && !this.fullName.equals("") && !this.fullName.contains(customer.fullName)) {
			return false;
		}
		if (this.customerId != null && !this.customerId.equals("") && !this.customerId.contains(customer.customerId)) {
			return false;
		}
		if (this.email != null && !this.email.equals("") && !this.email.contains(customer.email)) {
			return false;
		}
		if (this.password != null && !this.password.equals("") && !this.password.contains(customer.password)) {
			return false;
		}
		if (customer.birthday != null && !this.birthday.equals(customer.birthday)) {
			return false;
		}
		if (this.phoneNumber != null && !this.phoneNumber.equals("") && !this.phoneNumber.contains(customer.phoneNumber)) {
			return false;
		}
		return true;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Customer) {
			return this.customerId.equals(((Customer) obj).customerId);
		}
		return false;
	}
}
