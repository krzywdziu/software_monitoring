package com.zstwp.mans.controllers;

import com.zstwp.mans.database.entities.Alert;
import com.zstwp.mans.database.entities.AlertSeverity;
import com.zstwp.mans.database.entities.AlertStatus;
import com.zstwp.mans.services.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alert")
public class AlertController {

    private final AlertService alertService;

    @GetMapping("/all")
    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    @GetMapping("/severity/{severity}")
    public List<Alert> getAlertsBySeverity(@PathVariable AlertSeverity severity) {
        return alertService.getAlertsBySeverity(severity);
    }

    @GetMapping("/status/{status}")
    public List<Alert> getAlertsByStatus(@PathVariable AlertStatus status) {
        return alertService.getAlertsByStatus(status);
    }

    @GetMapping("/ip/{boxIp}")
    public List<Alert> getAlertsBySeverity(@PathVariable String boxIp) {
        return alertService.getAlertsByBoxIp(boxIp);
    }

}
