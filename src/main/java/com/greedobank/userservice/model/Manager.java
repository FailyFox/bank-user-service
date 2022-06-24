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

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "personId", referencedColumnName = "id")
  private Person person;

  @OneToMany(mappedBy = "manager")
  private List<FamilyMember> familyMembers;
}