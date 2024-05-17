package com.example.clinic.entity;


import com.example.clinic.enums.RolesEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
@Table(name= "role")
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RolesEnum name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}
