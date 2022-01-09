package com.techwithmaddy.CustomerAPI.controller;

import com.techwithmaddy.CustomerAPI.exception.CustomerNotFoundException;
import com.techwithmaddy.CustomerAPI.model.Customer;
import com.techwithmaddy.CustomerAPI.repository.CustomerRepository;
import com.techwithmaddy.CustomerAPI.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(method = {POST}, path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer saveCustomer(@Valid @RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @RequestMapping(method = {GET}, path = "/retrieve")
    public Customer getCustomerByEmail(@RequestParam String email){
        return customerService.getCustomerByEmail(email).orElseThrow(CustomerNotFoundException::new);
    }

    @RequestMapping(method = {PUT}, path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer updateExistingCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @RequestMapping(method = {PATCH}, path = "/update/{id}/{phoneNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updatePartialCustomer(@PathVariable Integer id, @PathVariable String phoneNumber) {
        try {
            Customer customer = customerRepository.findById(id).get();
            customer.setPhoneNumber(phoneNumber);
            return new ResponseEntity<Customer>(customerService.saveCustomer(customer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
