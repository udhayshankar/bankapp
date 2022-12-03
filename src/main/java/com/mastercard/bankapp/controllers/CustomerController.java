package com.mastercard.bankapp.controllers;

import com.mastercard.bankapp.models.Customer;
import com.mastercard.bankapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody  Customer customer){
           return customerService.createCustomer(customer);
    }
    @GetMapping("/{customerId}")
    public Optional<Customer> getCustomerById(@PathVariable  Long customerId){
            return customerService.findCustomerByCustomerId(customerId);
    }

}
