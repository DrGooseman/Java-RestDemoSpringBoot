package com.bbs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerResource {
	
	@Autowired
	CustomerRepository repo;

	@GetMapping("/customers")
public List<Customer> getCustomers(){
	List<Customer> customers = (List<Customer>)repo.findAll();
	
	return customers;
}
	
	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable int id){
		  return repo.findById(id)
			      .orElseThrow(() -> new CustomerNotFoundException(id));
	}
	
	 @PostMapping("/customers")
	 public Customer createCustomer(@RequestBody Customer newCustomer) {
	    return repo.save(newCustomer);
	  }
}
