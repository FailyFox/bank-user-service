package com.greedobank.userservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    private int id;

    private String fname;
    private String lname;
    private String email;
    private String password;
    private String refreshToken;
    private String address;

    @OneToOne(mappedBy = "customer")
    private Customer customer;

    @OneToOne(mappedBy = "manager")
    private Manager manager;
}