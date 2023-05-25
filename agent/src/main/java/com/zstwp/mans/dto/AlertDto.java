package com.zstwp.mans.dto;

import java.time.LocalDateTime;

public record AlertDto(String description, AlertStatus status, String boxIp,
                       AlertSeverity severity, LocalDateTime timestamp) {
}
