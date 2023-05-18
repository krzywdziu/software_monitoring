package com.zstwp.mans.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "specializations")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Specialization {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Specialization specialization = (Specialization) o;
        return id != null && Objects.equals(id, specialization.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
