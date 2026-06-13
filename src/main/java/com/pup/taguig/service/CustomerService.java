package com.pup.taguig.service;
import com.pup.taguig.dto.CustomerRequestDTO;
import com.pup.taguig.dto.CustomerResponseDTO;

public interface CustomerService {
	
	public CustomerResponseDTO getCustomerById(Long id);
	public int addCustomer(CustomerRequestDTO customer);

}