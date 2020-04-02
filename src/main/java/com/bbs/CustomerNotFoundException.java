package com.bbs;

public class CustomerNotFoundException extends RuntimeException {

	CustomerNotFoundException(long id) {
		    super("Could not find customer " + id);
		  }
	
}
