package com.pup.taguig.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pup.taguig.dto.CustomerRequestDTO;
import com.pup.taguig.dto.CustomerResponseDTO;
import com.pup.taguig.model.Customer;
import com.pup.taguig.repository.CustomerMapper;
import com.pup.taguig.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerResponseDTO getCustomerById(Long id) {
        Customer customer = customerMapper.getCustomerById(id);

        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }

        CustomerResponseDTO response = new CustomerResponseDTO();
        response.setId(customer.getId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());
        response.setPhone(customer.getPhone());

        return response;
    }

    @Override
    public int addCustomer(CustomerRequestDTO request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        return customerMapper.addCustomer(customer);
    }

}
