package com.kechez.customer_project;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebController implements WebMvcConfigurer {
	
	@Autowired
	public CustomerService customerLayer;
	
	@Autowired
	public CustomerManagerService managerLayer = new CustomerManagerService();
	
	public long customersCreated = 0;
	public long managersCreated = 0;
	
//	List<Customer> customerList;
//	List<CustomerManager> managerList;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
		registry.addViewController("/customers").setViewName("customers");
		registry.addViewController("/managers").setViewName("managers");
		registry.addViewController("/error").setViewName("error");
	}
	
	/****************************
	 * HTML NAVIGATION FUNCTIONS
	 ****************************/
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/customers")
	public String showCustomerPage(Customer customer, Model model) {
		// Show customers, or error page if there are no managers yet
		List<CustomerManager> managers = managerLayer.getManagers();
		List<Customer> customers = customerLayer.getCustomers();
		model.addAttribute("customerTable", customers);
		if (managers.size() == 0)
		{
			model.addAttribute("errmsg", "You must onboard a manager before onboarding a customer!");
			return "error";
		}
		return "customers";
	}
	
	@GetMapping("/managers")
	public String showManagerPage(CustomerManager customerManager, Model model) {
		List<CustomerManager> managers = managerLayer.getManagers();
		model.addAttribute("managerTable", managers);
		return "managers";
	}
	
	@GetMapping("/success")
	public String showSuccessPage(String msg, Model model) {
		model.addAttribute("msg", msg);
		return "success";
	}
	
	
	@GetMapping("/error")
	public String showErrorPage(String errmsg, Model model) {
		if (errmsg == "")
			errmsg = "An unexpected error occurred!";
		model.addAttribute("errmsg", errmsg);
		return "error";
	}
	
	
	/********************************
	 * USER --> CONTROLLER FUNCTIONS
	 ********************************/
	@PostMapping("/customers")
	public String onboardCustomer(@Valid Customer customer, BindingResult bindingResult, Model model) {
		// Attempt to onboard a customer
		
		List<Customer> customers = customerLayer.getCustomers();
		model.addAttribute("customerTable", customers);
		if (bindingResult.hasErrors())
			return "customers";
		
		createCustomer(customer);
		return showSuccessPage("Successfully added Customer " + customer.getName() + " (ID=" + customer.getCustomerID() + "), "
								+ "managed by " + customer.getManager().getName() + " (ID= " + customer.getManager().getManagerID() + ") !", model);
	}
	
	@PostMapping("/managers")
	public String onboardManager(@Valid CustomerManager manager, BindingResult bindingResult, Model model) {
		// Attempt to onboard a manager
		
		List<CustomerManager> managers = managerLayer.getManagers();
		model.addAttribute("managerTable", managers);
		if (bindingResult.hasErrors())
			return "managers";
		createManager(manager);
		return showSuccessPage("Successfully added Manager " + manager.getName() + " (ID=" + manager.getManagerID() + ")!", model);
	}
	
	
	/************************************
	 * CONTROLLER --> SERVICE FUNCTIONS
	 ************************************/
	
	/*****************************************************************
	 * Communicates with the Customer Service Layer to add a Customer
	 * Returns true if successful (there is a manager)
	 *****************************************************************/
	private void createCustomer(Customer customer) {
		CustomerManager manager = managerLayer.getRandomManager();
		customer.setManager(manager);
		customer.setCustomerID(++customersCreated);
		customerLayer.addCustomer(customer);
	}
	
	/******************************************************
	 * Communicates with the CustomerManager Service Layer
	 * to add a Customer Manager.
	 ******************************************************/
	private void createManager(CustomerManager manager) {
		manager.setManagerID(++managersCreated);
		managerLayer.addManager(manager);
	}
	
	/*******************************************************************
	 * Given a list, converts to a list with a new element on each line
	 *******************************************************************/
	private String listToString(List list, String newLineChar) {
		String string = "";
		
		for (int i = 0; i < list.size(); i++)
			string += list.get(i) + newLineChar;
		return string;
	}
}
