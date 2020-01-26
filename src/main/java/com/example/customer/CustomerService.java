package com.example.customer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	public List<Customer> findAll(){
		List<Customer> customersList=new ArrayList<Customer>();
		Iterable<Customer> customersIterator=customerRepository.findAll();
		
		for (Customer customer : customersIterator) {
			customersList.add(customer);
		}
		
		return customersList;
	}
}
