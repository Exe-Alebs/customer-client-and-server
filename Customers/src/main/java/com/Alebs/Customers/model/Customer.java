package com.Alebs.Customers.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotNull
    private String username;

    @NotNull
     private String fullName;

    @NotNull
    @Email
     private String email;

    @NotNull
    @Column
    private String password;

}
