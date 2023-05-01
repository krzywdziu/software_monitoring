package com.mans.mans.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "serviceman")
public class ServicemanEntity {

    @Id
    @Column(name = "serviceman_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int servicemanId;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "company")
    private String company;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "specialization")
    private String specialization;

    @OneToMany(mappedBy = "servicemanEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlertEntity> alertEntities = new ArrayList<>();
}
