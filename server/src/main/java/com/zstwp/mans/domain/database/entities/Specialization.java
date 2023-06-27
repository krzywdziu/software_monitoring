package com.zstwp.mans.domain.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specializations")
public class Specialization {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private UserSpecialization name;

    @JsonIgnore //StackOverflowError
    @ManyToMany(mappedBy = "specializations", fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    public Specialization(UserSpecialization specialization) {
        this.name = specialization;
    }

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
