package com.techwithmaddy.CustomerAPI.service;

import com.techwithmaddy.CustomerAPI.model.Customer;
import com.techwithmaddy.CustomerAPI.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer savedCustomer) {
        Customer customer = new Customer();
        customer.setFirstName(savedCustomer.getFirstName());
        customer.setLastName(savedCustomer.getLastName());
        customer.setEmail(savedCustomer.getEmail());
        customer.setPhoneNumber(savedCustomer.getPhoneNumber());
        return customerRepository.save(savedCustomer);
    }

    public Optional<Customer> getCustomerByEmail(String email){
        Customer customer = customerRepository.findCustomerByEmail(email);

        return Optional.ofNullable(customer);

    }


}


