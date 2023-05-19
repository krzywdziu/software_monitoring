package com.zstwp.mans.services;

import com.zstwp.mans.database.entities.Alert;
import com.zstwp.mans.database.entities.AlertSeverity;
import com.zstwp.mans.database.entities.AlertStatus;
import com.zstwp.mans.database.repositories.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
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

}
