package com.codegym.cms.repository;

import com.codegym.cms.model.Customer;
import com.codegym.cms.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CustomerRepository
        //extends Repository<Customer, Long>
        extends PagingAndSortingRepository<Customer, Long>
{
    Iterable<Customer> findAllByProvince(Province province);
}
