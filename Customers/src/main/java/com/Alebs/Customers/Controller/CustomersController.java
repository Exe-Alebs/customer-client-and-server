package com.Alebs.Customers.Controller;


import com.Alebs.Customers.dto.CustomersDTO;
import com.Alebs.Customers.exception.CustomerNotFoundException;
import com.Alebs.Customers.model.Customer;
import com.Alebs.Customers.repository.CustomerRepository;
import com.Alebs.Customers.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class CustomersController {

    private final CustomerRepository customerRepository;

    private final CustomerService customerService;

     @PostMapping("/customer")
    Customer newCustomer(@RequestBody Customer newCustomer){
         return customerRepository.save(newCustomer);
     }

     @GetMapping("/allcustomers")
     public List<Customer> getAllCustomers(@RequestParam(name = "search", required = false) String searchQuery){
         List<Customer> allCustomers = customerService.getAllCustomers();
         if (searchQuery != null){
             allCustomers = allCustomers.stream().filter(customer -> customer.getUsername().contains(searchQuery)).collect(Collectors.toList());
         }
         return allCustomers;
     }
      @GetMapping("/customers")
    public ResponseEntity<CustomersDTO> getCustomers(
            @RequestParam("page") int page,
            @RequestParam("size") int  size){
         CustomersDTO customers = customerService.getCustomers(page-1 , size);
         return new ResponseEntity<>(customers, HttpStatus.OK);
      }


      @GetMapping("/customer/{id}")
    Customer getCustomerById(@PathVariable Long id){
         return customerRepository.findById(id)
                 .orElseThrow(()->new CustomerNotFoundException(id));
      }

      @PutMapping("/customer/{id}")
    Customer updateCustomer(@RequestBody Customer updateCustomer, @PathVariable Long id){
         return customerRepository.findById(id)
                 .map(customer -> {
                     customer.setUsername(updateCustomer.getUsername());
                     customer.setEmail(updateCustomer.getEmail());
                     customer.setFullName(updateCustomer.getFullName());
                     return customerRepository.save(customer);
                 }).orElseThrow(()-> new CustomerNotFoundException(id));
      }

      @DeleteMapping("/customer/{id}")
    String deleteCustomers(@PathVariable Long id){
         if(!customerRepository.existsById(id)){
             throw new CustomerNotFoundException(id);
         }
 customerRepository.deleteById(id);
         return "User with id " + id + " has been deleted successfully.";
      }


}
