package com.zstwp.mans.database.repositories;

import com.zstwp.mans.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);

//    Optional<UserModel> findByOrganisation(String name);


}
