package com.greedobank.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue
    private Integer id;

    private String phone;
    private String idCode;
    private String passportData;
    private LocalDate birthday;

    @OneToOne
    @JoinColumn(name = "personId")
    private Person customer;
}