package com.mans.mans.repositories;

import com.mans.mans.entities.VmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VmRepository extends JpaRepository<VmEntity, Integer> {
}
