package com.zstwp.mans.database.repositories;

import com.zstwp.mans.database.entities.Alert;
import com.zstwp.mans.database.entities.Specialization;
import com.zstwp.mans.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    boolean existsByEmail(String email);

    @Query(
        value = """
            SELECT u.*
            FROM users u
            WHERE u.id IN (
                SELECT us.user_id
                FROM users_specializations us
                JOIN specializations s ON us.specialization_id = s.id
                WHERE s.name = ?)
            """ ,
        nativeQuery = true
    )
    List<User> findUsersBySpecializationName(String specialization);
    @Query(
        value = """
            SELECT *
            FROM users
            WHERE id = (
                SELECT a.user_id
                FROM alerts a
                    JOIN users_specializations us2 on a.user_id = us2.user_id
                WHERE us2.specialization_id = (SELECT id FROM specializations WHERE name = ?)
                GROUP BY a.user_id
                ORDER BY COUNT(a.user_id)
                LIMIT 1)
            LIMIT 1;
            """,
        nativeQuery = true
    )
    Optional<User> findUserBySpecializationAndWithFewestAlertsAssigned(String specialisation);

//    Optional<User> findUserBySpecialization;


}
