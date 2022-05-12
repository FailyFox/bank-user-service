package com.greedobank.userservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    private int id;

    private String phone;
    private String idCode;
    private String passportData;
    private LocalDate birthday;

    @OneToOne
    @JoinColumn(name = "personId")
    private Person customer;
}