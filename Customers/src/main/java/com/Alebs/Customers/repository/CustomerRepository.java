package com.Alebs.Customers.repository;

import com.Alebs.Customers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findOneByEmailAndPassword(String email, String password);

    Customer findByEmail(String email);

}
