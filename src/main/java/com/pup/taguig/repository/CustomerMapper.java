package com.pup.taguig.repository;

import org.apache.ibatis.annotations.Mapper;

import com.pup.taguig.model.Customer;

@Mapper
public interface CustomerMapper {
	
	public Customer getCustomerById(Long id);
	public int addCustomer(Customer customer);
}
