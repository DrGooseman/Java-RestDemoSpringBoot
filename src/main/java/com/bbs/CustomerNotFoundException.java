package com.bbs;

public class CustomerNotFoundException extends RuntimeException {

	CustomerNotFoundException(int id) {
		    super("Could not find customer " + id);
		  }
	
}
