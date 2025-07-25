package com.kechez.customer_project;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerManagerService {
	
	@Autowired
	private CustomerManagerRepositoryImpl managerTable;
	
	public void addManager(CustomerManager manager) {
		managerTable.save(manager);
	}
	
	public List<CustomerManager> getManagers() {
		List<CustomerManager> managers = new ArrayList<CustomerManager>();
		managerTable.findAll().forEach(managers::add);
		return managers;
	}
	
	public CustomerManager getRandomManager() {
		return managerTable.findRandomManager();
	}
	
	public CustomerManager getFirstManager() {
		return managerTable.findAll().get(0);
	}
	
	public boolean managersExist() {
		return managerTable.count() > 0;
	}
}
