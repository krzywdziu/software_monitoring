package com.zstwp.mans.domain.database.repositories;

import com.zstwp.mans.domain.database.entities.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    List<Specialization> findAll();
}
