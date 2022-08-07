package com.jon.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jon.springdemo.entity.Customer;
import com.jon.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire te customer service
	@Autowired
	private CustomerService customerService;

	// add mapping for get customers
	@GetMapping("/customers")
	public List<Customer> getCustomer() {
		return customerService.getCustomers();

	}

	// add mapping for get single customer
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer with id: " + customerId + " could not be found");
		}

		return customer;

	}

	@PostMapping("/customers")
	public Customer saveCustomer(@RequestBody Customer customer) {
		customer.setId(0);
		customerService.saveCustomer(customer);
		return customer;
	}

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return customer;
	}

	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {

		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer with id: " + customerId + " could not be found");
		}

		customerService.deleteCustomer(customerId);
		return "Deleted customer with id: " + customerId;
	}
}
