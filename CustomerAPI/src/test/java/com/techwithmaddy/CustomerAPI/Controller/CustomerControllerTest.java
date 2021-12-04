package com.techwithmaddy.CustomerAPI.Controller;

import com.techwithmaddy.CustomerAPI.controller.CustomerController;
import com.techwithmaddy.CustomerAPI.controllertest.AbstractTest;
import com.techwithmaddy.CustomerAPI.exception.CustomerNotFoundException;
import com.techwithmaddy.CustomerAPI.model.Customer;
import com.techwithmaddy.CustomerAPI.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void shouldSaveCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setEmail("email@test.com");
        customer.setPhoneNumber("0123456789");

        mockMvc.perform(post("/customer/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldGetCustomerByEmail() throws Exception {
        Customer customer = new Customer();
        String email = "steve@austin.com";
        Optional<Customer> customerOptional = Optional.of(customer);

        when(customerService.getCustomerByEmail(email)).thenReturn(customerOptional);

        mockMvc.perform(get(String.format("/customer/retrieve"))
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("email", email))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldThrowExceptionIfEmailNotFound() throws Exception {
        String email = "test@email.com";

        doThrow(new CustomerNotFoundException()).when(customerService).getCustomerByEmail(email);

        mockMvc.perform(get(String.format("/customer/retrieve"))
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("email", email))
                .andExpect(status().isNotFound());

    }




}
