package com.techwithmaddy.CustomerAPI.repository;

import com.techwithmaddy.CustomerAPI.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldFindCustomerByEmail() {
        Customer customer = customerRepository.findCustomerByEmail("richard@branson.com");
        assertThat(customerRepository.findCustomerByEmail(customer.getEmail())).isEqualTo(customer);
    }
}
