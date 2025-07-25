package com.kechez.customer_project;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepositoryImpl extends CrudRepository<Customer, Long> {
	
}
