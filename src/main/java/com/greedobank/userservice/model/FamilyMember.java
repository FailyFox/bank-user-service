package com.greedobank.userservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "familyMember")
@Getter
@Setter
@NoArgsConstructor
public class FamilyMember {
    @Id
    private int id;

    private String fname;
    private String lname;
    private String phone;
    private String address;

    @ManyToOne
    @JoinColumn(name = "managerId")
    private Manager manager;
}