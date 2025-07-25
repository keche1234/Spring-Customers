package com.kechez.customer_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = { "com.kechez.customer_project "})
@ComponentScan
public class Driver {

	public static void main(String[] args) {
		SpringApplication.run(Driver.class, args);
	}

}
