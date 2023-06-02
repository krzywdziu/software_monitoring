package com.zstwp.mans.services;

import com.zstwp.mans.database.entities.Alert;
import com.zstwp.mans.database.entities.AlertSeverity;
import com.zstwp.mans.database.entities.AlertStatus;
import com.zstwp.mans.database.repositories.AlertRepository;
import com.zstwp.mans.dto.AlertDto;
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

    public Alert getAlertById(long id) {
        return alertRepository.findAlertById(id);
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

    public void assignAlertToUser(long alertId, long userId) {
        alertRepository.assignAlertToUser(alertId, userId);
    }

    public void updateAlertStatus(long id, AlertStatus status) {
        alertRepository.updateAlertStatus(id, status);
    }

    public void addNewAlert(AlertDto alert) {
//        String/AlertDto -> Alert
//        Alert alert = new Alert(alertDto);

        // check if alert already exists (in the db)
        if (true) {
            alertRepository.save(new Alert(alert));
        }

    }

//    TODO:
    public void updateAlertById() {
//       update status, assign user, ...

    }

    public int countAllAlerts() {
        return (int) alertRepository.count();
    }

}
