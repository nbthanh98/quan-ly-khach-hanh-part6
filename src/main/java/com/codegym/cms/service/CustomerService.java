package com.codegym.cms.service;

import com.codegym.cms.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findCustomerById(Long id);

    void save(Customer customer);

    void remoteCustomer(Long id);
}
