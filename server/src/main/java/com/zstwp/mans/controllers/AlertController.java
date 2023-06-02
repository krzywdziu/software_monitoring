package com.zstwp.mans.controllers;

import com.zstwp.mans.database.entities.Alert;
import com.zstwp.mans.database.entities.AlertSeverity;
import com.zstwp.mans.database.entities.AlertStatus;
import com.zstwp.mans.services.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService alertService;

    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    @GetMapping("/{id}")
    public Alert getAlertById(@PathVariable long id) {
        return alertService.getAlertById(id);
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

    @GetMapping("/user")
    public List<Alert> getAlertsByUser(@RequestParam long id) {
        return alertService.getAlertsByUserId(id);
    }

    @GetMapping("/count")
    public int countAlerts() {
        return alertService.countAllAlerts();
    }

    @PutMapping("/{id}")
    public void assignAlert() {

        long alertId = 1L;
        long userId = 1L;

        alertService.assignAlertToUser(alertId, userId);
        return;
    }

    @PutMapping("/{id}")
    public void updateAlertStatus() {
        long id = 1L;
        AlertStatus status = AlertStatus.RESOLVED;

        alertService.updateAlertStatus(id, status);
    }

//    @PutMapping(path = "{studentId}")
//    public void updateStudent(
//            @PathVariable("studentId") Integer studentId,
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String email) {
//        studentService.updateStudent(studentId, name, email);
//    }
}
