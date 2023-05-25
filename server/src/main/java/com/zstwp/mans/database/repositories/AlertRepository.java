package com.zstwp.mans.database.repositories;

import com.zstwp.mans.database.entities.Alert;
import com.zstwp.mans.database.entities.AlertSeverity;
import com.zstwp.mans.database.entities.AlertStatus;
import com.zstwp.mans.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findAllBySeverity(AlertSeverity severity);

    Alert findAlertById(long id);

    List<Alert> findAllByBoxIp(String boxIp);

    List<Alert> findAllByStatus(AlertStatus status);

    List<Alert> findAllByUserId(long id);
}
