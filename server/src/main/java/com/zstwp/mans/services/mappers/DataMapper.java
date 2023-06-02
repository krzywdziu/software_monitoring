package com.zstwp.mans.services.mappers;

import com.zstwp.mans.utils.RandomEnumGenerator;
import com.zstwp.mans.database.entities.AlertSeverity;
import com.zstwp.mans.database.entities.AlertStatus;
import com.zstwp.mans.dto.AlertDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DataMapper {

    public AlertDto mapData(String message) {
        RandomEnumGenerator reg = new RandomEnumGenerator<>(AlertSeverity.class);
        AlertSeverity severity = (AlertSeverity) reg.randomEnum();

        AlertDto alertDto = new AlertDto(
                message,
                AlertStatus.UNASSIGNED,
                "10.0.8.137",
                severity,
                LocalDateTime.now()
        );

        return alertDto;
    }
}
