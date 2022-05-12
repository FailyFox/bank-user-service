package com.greedobank.userservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "manager")
@Getter
@Setter
@NoArgsConstructor
public class Manager {
    @Id
    private int id;

    @OneToOne
    @JoinColumn(name = "personId")
    private Person manager;

    @OneToMany(mappedBy = "manager")
    private List<FamilyMember> familyMembers;
}