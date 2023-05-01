package com.mans.mans.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vm")
public class VmEntity {

    @Id
    @Column(name = "host_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hostId;

    @Column(name = "company")
    private String company;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "vmEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlertEntity> alertEntities = new ArrayList<>();
}
