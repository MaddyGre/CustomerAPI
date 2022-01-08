package com.techwithmaddy.CustomerAPI.controller;

import com.techwithmaddy.CustomerAPI.exception.CustomerNotFoundException;
import com.techwithmaddy.CustomerAPI.model.Customer;
import com.techwithmaddy.CustomerAPI.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = {POST}, path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Customer saveCustomer(@Valid @RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @RequestMapping(method = {GET}, path = "/retrieve")
    @ResponseBody
    public Customer getCustomerByEmail(@RequestParam String email){
        return customerService.getCustomerByEmail(email).orElseThrow(CustomerNotFoundException::new);
    }

}
