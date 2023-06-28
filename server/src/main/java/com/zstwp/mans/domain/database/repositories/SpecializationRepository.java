package com.zstwp.mans.domain.database.repositories;

import com.zstwp.mans.domain.database.entities.Specialization;
import com.zstwp.mans.domain.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    List<Specialization> findAll();
    Set<Specialization> findAllByUsers(User user);
}
