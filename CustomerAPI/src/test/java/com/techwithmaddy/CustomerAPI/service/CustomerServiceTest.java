package com.techwithmaddy.CustomerAPI.service;

import com.techwithmaddy.CustomerAPI.controllertest.AbstractTest;
import com.techwithmaddy.CustomerAPI.model.Customer;
import com.techwithmaddy.CustomerAPI.repository.CustomerRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest extends AbstractTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void shouldSaveCustomerSuccessfully() {
        Customer customer = new Customer();
        customer.setFirstName("Linda");
        customer.setLastName("Jackson");
        customer.setEmail("linda@jackson.com");
        customer.setPhoneNumber("0123456789");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.saveCustomer(customer);

        assertThat(savedCustomer).isNotNull();

        verify(customerRepository, times(1)).save(any(Customer.class));

    }

    @Test
    public void shouldGetCustomerByEmail() {
        Customer customer = new Customer();
        customer.setFirstName("Steve");
        customer.setLastName("Austin");
        customer.setEmail("steve@austin.com");
        customer.setPhoneNumber("0123456789");

        Mockito.when(customerRepository.findCustomerByEmail(customer.getEmail())).thenReturn(customer);

        Optional<Customer> retrievedCustomer = customerService.getCustomerByEmail(customer.getEmail());

        Assertions.assertTrue(retrievedCustomer.isPresent());

    }
}
