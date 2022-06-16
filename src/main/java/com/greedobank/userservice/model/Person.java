package com.greedobank.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String fname;
  private String lname;
  private String email;
  private String password;
  private String refreshToken;
  private String address;

  @OneToOne(mappedBy = "person", optional = false)
  private Customer customer;

  @OneToOne(mappedBy = "person", optional = false)
  private Manager manager;
}