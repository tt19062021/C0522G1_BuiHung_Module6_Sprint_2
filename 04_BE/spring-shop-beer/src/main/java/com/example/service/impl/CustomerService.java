package com.example.service.impl;

import com.example.model.customer.Customer;
import com.example.repository.ICustomerRepository;
import com.example.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Customer findCustomerByUsername(String username) {
        return customerRepository.findCustomerByUserName(username);
    }
}
