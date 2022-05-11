package com.greedobank.userservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class Customer {
    @Id
    @Column(nullable = false)
    private int id;

    private String phone;
    private String idCode;
    private String passportData;
    private LocalDate birthday;

    @OneToOne
    @JoinColumn(name = "personId")
    private Person customer;
}