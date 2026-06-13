package com.pup.taguig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pup.taguig.service.CustomerService;
import com.pup.taguig.dto.CustomerRequestDTO;
import com.pup.taguig.dto.CustomerResponseDTO;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/{id}")
	public CustomerResponseDTO getCustomerById(@PathVariable Long id) {
		return customerService.getCustomerById(id);
	}

	@PostMapping
	public int addCustomer(@RequestBody CustomerRequestDTO request) {
		return customerService.addCustomer(request);
	}

}
