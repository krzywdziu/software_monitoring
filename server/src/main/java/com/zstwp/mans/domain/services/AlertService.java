package com.zstwp.mans.domain.services;

import com.zstwp.mans.domain.database.entities.Alert;
import com.zstwp.mans.domain.database.entities.AlertSeverity;
import com.zstwp.mans.domain.database.entities.AlertStatus;
import com.zstwp.mans.domain.database.repositories.AlertRepository;
import com.zstwp.mans.domain.dto.AlertDto;
import com.zstwp.mans.domain.services.exceptions.AlertNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final UserService userService;

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public Alert getAlertById(long id) {
        return alertRepository.findAlertById(id)
                .orElseThrow(() -> new AlertNotFoundException("Alert not found with id " + id));
    }

    public List<Alert> getAlertsBySeverity(AlertSeverity severity) {
        return alertRepository.findAllBySeverity(severity);
    }

    public List<Alert> getAlertsByBoxIp(String ip) {
        return alertRepository.findAllByBoxIp(ip);
    }

    public List<Alert> getAlertsByStatus(AlertStatus status) {
        return alertRepository.findAllByStatus(status);
    }

    public List<Alert> getAlertsByUserId(long id) {
        return alertRepository.findAllByUserId(id);
    }

    public Integer updateAlertStatus(long id, AlertStatus status) {
        return alertRepository.updateAlertStatus(id, status);
    }

    public Integer assignAlertToUser(long alertId, long userId) {
        return alertRepository.assignAlertToUser(alertId, userService.getUserById(userId));
    }

    public void addNewAlert(AlertDto alert) {
//        String/AlertDto -> Alert
//        Alert alert = new Alert(alertDto);

        // check if alert already exists (in the db)
        if (true) {
            alertRepository.save(new Alert(alert));
        }

    }

    public int countAllAlerts() {
        return (int) alertRepository.count();
    }

}
