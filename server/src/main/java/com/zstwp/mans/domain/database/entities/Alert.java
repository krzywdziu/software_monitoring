package com.zstwp.mans.domain.database.entities;

import com.zstwp.mans.domain.dto.AlertDto;
import com.zstwp.mans.kafka.KafkaAlertDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "alerts", indexes = @Index(columnList = "user_id"))
public class Alert {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String description;

    private AlertStatus status;

    private String boxIp;

    private AlertSeverity severity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private LocalDateTime timestamp;

    public Alert(AlertDto alertDto) {
        this.description = alertDto.getDescription();
        this.boxIp = alertDto.getBoxIp();
        this.severity = alertDto.getSeverity();
        this.timestamp = alertDto.getTimestamp();
//        this.status = AlertStatus.UNASSIGNED;
        this.status = user == null ? AlertStatus.UNASSIGNED : AlertStatus.ASSIGNED;
    }
}
