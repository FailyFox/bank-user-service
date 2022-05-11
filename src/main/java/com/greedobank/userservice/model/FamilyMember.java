package com.greedobank.userservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class FamilyMember {
    @Id
    @Column(nullable = false)
    private int id;

    private String fname;
    private String lname;
    private String phone;
    private String address;

    @ManyToOne
    @JoinColumn(name = "managerId")
    private Manager manager;
}