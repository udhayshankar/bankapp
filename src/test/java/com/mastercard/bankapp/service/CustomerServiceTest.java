package com.mastercard.bankapp.service;


import com.mastercard.bankapp.models.Customer;
import com.mastercard.bankapp.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void findCustomerByCustomerIdTest(){
        Optional<Customer> custStub = createCustomerStub();
        when(customerRepository.findById(custStub.get().getCustomerId())).thenReturn(custStub);
        Optional<Customer> cust = customerService.findCustomerByCustomerId(custStub.get().getCustomerId());
        Assertions.assertEquals(cust.get().getCustomerId(),custStub.get().getCustomerId());
    }

    @Test
    public void createAccountTest(){
        Optional<Customer> custStub = createCustomerStub();
        when(customerRepository.saveAndFlush(custStub.get())).thenReturn(custStub.get());
        Customer cust =  customerService.createCustomer(custStub.get());
        System.out.println("helloworl"+cust.getCustomerId());
        Assertions.assertEquals(cust.getCustomerId(),custStub.get().getCustomerId());
    }

    @Test
    public void fetchAllAccountsTest(){
        List<Customer> custStubList = createCustomerListStub();
        when(customerRepository.findAll()).thenReturn(createCustomerListStub());
        List<Customer> customerList = customerService.fetchAllCustomers();
        Assertions.assertEquals(customerList.size(),custStubList.size());
    }

    private Optional<Customer> createCustomerStub(){
        Customer customerStub = Customer.builder()
                .customerId(3L)
                .name("subram")
                .gender("male")
                .phoneNumber("0896789011")
                .email("bama@gmail.com")
                .password("password")
                .address("No 99, icon living apartment")
                .city("Cork")
                .county("cork")
                .country("Ireland")
                .eirCode("C02 KY67")
                .build();
        return Optional.of(customerStub);
    }
    private List<Customer> createCustomerListStub(){
        List<Customer> customerList =  new ArrayList<>();
        customerList.add(createCustomerStub().get());
        return customerList;
    }
}
