package com.zstwp.mans.database.repositories;

import com.zstwp.mans.database.entities.Alert;
import com.zstwp.mans.database.entities.AlertSeverity;
import com.zstwp.mans.database.entities.AlertStatus;
import com.zstwp.mans.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findAllBySeverity(AlertSeverity severity);

    Optional<Alert> findAlertById(long id);

    List<Alert> findAllByBoxIp(String boxIp);

    List<Alert> findAllByStatus(AlertStatus status);

    List<Alert> findAllByUserId(long id);

    @Modifying
    @Transactional
    @Query("update Alert set user = :user where id = :alertId")
    Integer assignAlertToUser(long alertId, User user);

    @Modifying
    @Transactional
    @Query("update Alert set status = :status where id = :id")
    Integer updateAlertStatus(long id, AlertStatus status);
}
