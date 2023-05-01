package com.mans.mans.repositories;

import com.mans.mans.entities.ServicemanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicemanRepository extends JpaRepository<ServicemanEntity, Integer> {
}
