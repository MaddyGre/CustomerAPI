package com.techwithmaddy.CustomerAPI.repository;

import com.techwithmaddy.CustomerAPI.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.email =:email")
    Customer findCustomerByEmail(String email);

}
