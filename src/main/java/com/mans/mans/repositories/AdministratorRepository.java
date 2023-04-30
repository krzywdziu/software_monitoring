package com.mans.mans.repositories;

import com.mans.mans.entities.AdministratorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorRepository extends JpaRepository<AdministratorEntity, Integer> {
    AdministratorEntity  findByAdministratorId(int administratorId);
}
