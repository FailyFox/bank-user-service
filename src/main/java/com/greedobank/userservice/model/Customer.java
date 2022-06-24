package com.greedobank.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Getter
@Setter
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String phone;
  private String idCode;
  private String passportData;
  private LocalDate birthday;

  @OneToOne(cascade = CascadeType.ALL)
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name = "personId", referencedColumnName = "id")
  private Person person;
}