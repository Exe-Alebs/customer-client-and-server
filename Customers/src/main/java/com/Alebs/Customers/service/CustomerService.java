package com.Alebs.Customers.service;

import com.Alebs.Customers.dto.CustomersDTO;
import com.Alebs.Customers.dto.LoginDTO;
import com.Alebs.Customers.model.Customer;
import com.Alebs.Customers.payload.LoginResponse;

import java.util.List;

public interface CustomerService {
    CustomersDTO getCustomers(int page, int size);

    List<Customer> getAllCustomers();

    String addCustomers(CustomersDTO customersDTO);

    LoginResponse LOGIN_RESPONSE(LoginDTO loginDTO);


}
