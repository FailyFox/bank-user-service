package com.greedobank.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Manager {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "personId")
  private Person person;

  @OneToMany(mappedBy = "manager")
  private List<FamilyMember> familyMembers;
}