package com.kechez.customer_project;

import java.util.Iterator;
import java.util.Random;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Component;

@Repository
public interface CustomerManagerRepositoryImpl extends JpaRepository<CustomerManager, Long> {
	CustomerManager findByName(String name);
	default CustomerManager findRandomManager()
	{
		// random number [0, size), get id, findOne
		int count = (int) this.count();
		int steps = new Random().nextInt((int) count);
		Iterator<CustomerManager> iter = findAll().iterator();
		
		//table[0]->table[1]->table[2]
		for (int i = 0; i < steps; i++) // i = 0, 1
			iter.next();
		
		return iter.next();
	}
}
