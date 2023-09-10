package com.Alebs.Customers.dto;


import com.Alebs.Customers.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDTO {
    private List<Customer> customers;
    private  long totalCustomers;
    private long id;
    private  String email;

    private String password;
    private String fullName;
    private String username;
}
