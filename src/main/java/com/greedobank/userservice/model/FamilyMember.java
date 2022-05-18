package com.greedobank.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FamilyMember {
    @Id
    private Integer id;

    private String fname;
    private String lname;
    private String phone;
    private String address;

    @ManyToOne
    @JoinColumn(name = "managerId")
    private Manager manager;
}