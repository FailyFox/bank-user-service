package com.greedobank.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "personId", referencedColumnName = "id")
  private Person person;

  @OneToMany(mappedBy = "manager", orphanRemoval = true, cascade = CascadeType.PERSIST)
  private List<FamilyMember> familyMembers;
}