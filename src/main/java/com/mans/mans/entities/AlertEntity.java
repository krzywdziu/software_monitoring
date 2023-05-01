package com.mans.mans.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "alert")
public class AlertEntity {

    @Id
    @Column(name = "alert_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int alertId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id", nullable = false)
    private VmEntity vmEntity;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceman_id", nullable = false)
    private ServicemanEntity servicemanEntity;

    @Column(name = "status")
    private String status;
}
