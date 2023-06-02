package com.zstwp.mans.controllers;

import com.zstwp.mans.database.entities.Alert;
import com.zstwp.mans.database.entities.AlertSeverity;
import com.zstwp.mans.database.entities.AlertStatus;
import com.zstwp.mans.dto.AlertDto;
import com.zstwp.mans.services.AlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService alertService;

    @GetMapping
    public List<AlertDto> getAllAlerts() {
        List<Alert> alertList = alertService.getAllAlerts();
        List<AlertDto> alertDtoList = new ArrayList<>();

        alertList.stream().forEach(alert -> {
            alertDtoList.add(new AlertDto(alert));
        });
        return alertDtoList;
    }

    @GetMapping("/{id}")
    public AlertDto getAlertById(@PathVariable long id) {
        return new AlertDto(alertService.getAlertById(id));
    }

    @GetMapping("/severity/{severity}")
    public List<AlertDto> getAlertsBySeverity(@Valid @PathVariable AlertSeverity severity) {
        List<Alert> alertList = alertService.getAlertsBySeverity(severity);
        List<AlertDto> alertDtoList = new ArrayList<>();

        alertList.stream().forEach(alert -> {
            alertDtoList.add(new AlertDto(alert));
        });
        return alertDtoList;
    }

    @GetMapping("/status/{status}")
    public List<AlertDto> getAlertsByStatus(@Valid @PathVariable AlertStatus status) {
        List<Alert> alertList = alertService.getAlertsByStatus(status);
        List<AlertDto> alertDtoList = new ArrayList<>();

        alertList.stream().forEach(alert -> {
            alertDtoList.add(new AlertDto(alert));
        });
        return alertDtoList;
    }

    @GetMapping("/ip/{boxIp}")
    public List<AlertDto> getAlertsBySeverity(@PathVariable String boxIp) {
        List<Alert> alertList = alertService.getAlertsByBoxIp(boxIp);
        List<AlertDto> alertDtoList = new ArrayList<>();

        alertList.stream().forEach(alert -> {
            alertDtoList.add(new AlertDto(alert));
        });
        return alertDtoList;
    }

    @GetMapping("/user")
    public List<AlertDto> getAlertsByUser(@RequestParam long id) {
        List<Alert> alertList = alertService.getAlertsByUserId(id);
        List<AlertDto> alertDtoList = new ArrayList<>();

        alertList.stream().forEach(alert -> {
            alertDtoList.add(new AlertDto(alert));
        });
        return alertDtoList;
    }

//    @GetMapping("/count") // testing
//    public int countAlerts() {
//        return alertService.countAllAlerts();
//    }

    @PutMapping("{id}/update")
    public String updateAlert(@PathVariable long id, @RequestParam AlertStatus status) {
        return alertService.updateAlertStatus(id, status) + " Alert(s) updated";
    }

    @PutMapping("{id}/assign")
    public String assignAlert(@PathVariable(name = "id") long alertId, @RequestParam long userId) {
        return alertService.assignAlertToUser(alertId, userId) + " Alert(s) assigned";
    }
}
