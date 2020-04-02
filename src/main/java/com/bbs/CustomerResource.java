package com.bbs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Customer getCustomer(@PathVariable long id){
		  return repo.findById(id)
			      .orElseThrow(() -> new CustomerNotFoundException(id));
	}
	
	 @PostMapping("/customers")
	 public Customer createCustomer(@RequestBody Customer newCustomer) {
	    return repo.save(newCustomer);
	  }
	 
	 @PutMapping("/customers")
	 public Customer editCustomer(@RequestBody Customer newCustomer) {
		 
	    return repo.findById(newCustomer.getId())
	    	      .map(customer -> {
	    	    	  customer.setName(newCustomer.getName());
	    	    	  customer.setEmail(newCustomer.getEmail());
	    	          return repo.save(customer);
	    	        })
	    	        .orElseGet(() -> {
	    	          return repo.save(newCustomer);
	    	        });
	  }
	 
	 @DeleteMapping("/customers/{id}")
	 public void deleteCustomer(@PathVariable Long id) {
		 repo.deleteById(id);
	  }
}
