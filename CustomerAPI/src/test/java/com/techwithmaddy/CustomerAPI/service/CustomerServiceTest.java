package com.techwithmaddy.CustomerAPI.service;

import com.techwithmaddy.CustomerAPI.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void shouldSaveCustomerSuccessfully() {
        Customer customer = new Customer();
        customer.setFirstName("Richard");
        customer.setLastName("Branson");
        customer.setEmail("richard@branson.com");
        customer.setPhoneNumber("0112233445566");

        Customer savedCustomer = customerService.saveCustomer(customer);

        assertThat(savedCustomer).isNotNull();

    }

    @Test
    public void shouldGetCustomerByEmail() {
        Customer customer = new Customer();
        customer.setFirstName("Steve");
        customer.setLastName("Austin");
        customer.setEmail("steve@austin.com");
        customer.setPhoneNumber("01223344556");

        Optional<Customer> retrievedCustomer = customerService.getCustomerByEmail(customer.getEmail());

        assertEquals(retrievedCustomer.get().getFirstName(), customer.getFirstName());
        assertEquals(retrievedCustomer.get().getLastName(), customer.getLastName());
        assertEquals(retrievedCustomer.get().getEmail(), customer.getEmail());
        assertEquals(retrievedCustomer.get().getPhoneNumber(), customer.getPhoneNumber());

    }
}
