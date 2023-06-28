package com.zstwp.mans.domain.dto;

import com.zstwp.mans.domain.database.entities.Alert;
import com.zstwp.mans.domain.database.entities.AlertSeverity;
import com.zstwp.mans.domain.database.entities.AlertStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlertDto {
    long id;
    String description;
    AlertStatus status;
    String boxIp;
    AlertSeverity severity;
    LocalDateTime timestamp;
    UserDto userDto;
}
