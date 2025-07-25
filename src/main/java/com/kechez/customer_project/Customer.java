package com.kechez.customer_project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

@Entity(name="CUSTOMER")
public class Customer {
	@Id
	private Long customerID;
	
	@NotNull
	@Size(min=2)
	private String name;

	@NotNull
	@Size(min=10)
	@PositiveOrZero
	@Digits(fraction = 0, integer = 20)
	private String phoneNumber;
	
	@NotNull
	@Size(min=2)
	private String address;
	
	@ManyToOne
	private CustomerManager manager;

	public Customer() {
		name = "";
		phoneNumber = "";
		address = "";
		manager = null;
	}
	
	public Customer(String n, String phone, String addy, CustomerManager m, Long id) {
		name = n;
		phoneNumber = phone;
		address = addy;
		manager = m;
		
		customerID = id;
	}

	public String toString() {
		return name + " (" + address + ") (" + phoneNumber + ") [CUSTOMER " + customerID + "]";
	}
}
