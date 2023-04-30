package com.mans.mans.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "administrator")
public class AdministratorEntity {

    @Id
    @Column(name = "administrator_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int administratorId;

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
}
