package com.kechez.customer_project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepositoryImpl customerTable;
	
	public void addCustomer(Customer c) {
		customerTable.save(c);
	}
	
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		customerTable.findAll().forEach(customers::add);
		return customers;
	}
}
