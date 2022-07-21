package com.greedobank.userservice.model;

import com.greedobank.userservice.model.enums.Status;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

  @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "personId", referencedColumnName = "id")
  private Person person;

  @Enumerated(EnumType.STRING)
  private Status status;
}