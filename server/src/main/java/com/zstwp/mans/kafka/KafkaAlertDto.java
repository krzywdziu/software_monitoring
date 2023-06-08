package com.zstwp.mans.kafka;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.zstwp.mans.domain.database.entities.AlertSeverity;
import com.zstwp.mans.domain.database.entities.AlertStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class KafkaAlertDto {
    String description;
    AlertStatus status;
    String boxIp;
    AlertSeverity severity;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime timestamp;
}