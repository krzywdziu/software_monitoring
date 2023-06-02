package com.zstwp.mans.dto;

import com.zstwp.mans.database.entities.Alert;
import com.zstwp.mans.database.entities.AlertSeverity;
import com.zstwp.mans.database.entities.AlertStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class AlertDto {
    String description;
    AlertStatus status;
    String boxIp;
    AlertSeverity severity;
    LocalDateTime timestamp;

    public AlertDto(Alert alert) {
        this.description = alert.getDescription();
        this.status = alert.getStatus();
        this.boxIp = alert.getBoxIp();
        this.severity = alert.getSeverity();
        this.timestamp = alert.getTimestamp();
    }
}
