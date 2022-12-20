package com.example.service;

import com.example.model.customer.Customer;

public interface ICustomerService {
    Customer findCustomerByUsername(String username);
}
