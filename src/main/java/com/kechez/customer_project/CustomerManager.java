package com.kechez.customer_project;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

@Entity(name="MANAGER")
public class CustomerManager {
	@Id
	private Long managerID;
	
	@NotNull
	@Size(min=2)
	private String name;

	@OneToMany(mappedBy="customerID")
	private Set<Customer> customers;

	public CustomerManager() {
		name = "";
	}
	
	public CustomerManager(String n, Long id) {
		name = n;
		managerID = id;
	}

	public String toString() {
		return name + " [MANAGER " + managerID + "]";
	}
}
