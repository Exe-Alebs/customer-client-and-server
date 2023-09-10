package com.Alebs.Customers.service;

import com.Alebs.Customers.dto.CustomersDTO;
import com.Alebs.Customers.dto.LoginDTO;
import com.Alebs.Customers.model.Customer;
import com.Alebs.Customers.payload.LoginResponse;
import com.Alebs.Customers.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class CustomerServiceImpl  implements CustomerService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    CustomerRepository customerRepository;


    @Override
    public  String addCustomers(CustomersDTO customersDTO){

        Customer  customer = new Customer(
                customersDTO.getId(),
                customersDTO.getUsername(),
                customersDTO.getEmail(),
                customersDTO.getFullName(),

                this.passwordEncoder.encode(customersDTO.getPassword())

        );
        customerRepository.save(customer);
        return  customer.getUsername();

    }

    @Override
    public LoginResponse LOGIN_RESPONSE(LoginDTO loginDTO){
        String msg ="";
        Customer customer1 = customerRepository.findByEmail(loginDTO.getEmail());
        if (customer1 != null) {
            String password =loginDTO.getPassword();
            String encodedPassword = customer1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if(isPwdRight){
                Optional<Customer> customer = customerRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (customer.isPresent()){
                    return new LoginResponse("Login Success", true);
                }else {
                    return new LoginResponse("login failed", false);
                }
            }else {
                return new LoginResponse("password Not match", false);
            }

        }else {
            return  new LoginResponse("Email not exist", false);
        }

    }
    @Override
    public CustomersDTO getCustomers(int pageNumber, int sizePerPage){
        // Create a PageRequest object with the pageNumber, sizePerPage, and sorting if you want

        PageRequest pageRequest = PageRequest.of(pageNumber,sizePerPage);
        // Fetch the page of students using the pageRequest
        Page<Customer> customerPage = customerRepository.findAll(pageRequest);
        // Extract the list of students from the page
        List<Customer> customers = customerPage.getContent();

        long totalCustomers = customerPage.getTotalElements();

        CustomersDTO customersDTO = new CustomersDTO();
        customersDTO.setCustomers(customers);
      customersDTO.setTotalCustomers(totalCustomers);

      return customersDTO;





    }
    @Override
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
}
