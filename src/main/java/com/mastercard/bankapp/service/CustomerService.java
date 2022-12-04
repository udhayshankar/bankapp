package com.mastercard.bankapp.service;

import com.mastercard.bankapp.models.Customer;
import com.mastercard.bankapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer( Customer customer){
        return customerRepository.saveAndFlush(customer);
    }

    public Optional<Customer> findCustomerByCustomerId( Long customerId){
        return customerRepository.findById(customerId);
    }

    public List<Customer> fetchAllCustomers(){
        return customerRepository.findAll();
    }

}
