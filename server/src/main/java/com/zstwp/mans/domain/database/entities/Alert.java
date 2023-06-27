package com.zstwp.mans.domain.database.entities;

import com.zstwp.mans.domain.dto.AlertDto;
import jakarta.persistence.*;
import lombok.*;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private LocalDateTime timestamp;



    public Alert(AlertDto alertDto, User user) {
        this.description = alertDto.getDescription();
        this.boxIp = alertDto.getBoxIp();
        this.severity = alertDto.getSeverity();
        this.timestamp = alertDto.getTimestamp();
//        this.status = AlertStatus.UNASSIGNED;
        this.user = user;
        this.status = user == null ? AlertStatus.UNASSIGNED : AlertStatus.ASSIGNED;
    }
}
