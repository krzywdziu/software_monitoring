package com.zstwp.mans.database.entities;

import com.zstwp.mans.dto.AlertDto;
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
//@Table(name = "alerts")
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

//    @Column(length = 1024)
//    private String url;

    @CreationTimestamp
    private LocalDateTime timestamp;

    public Alert(AlertDto alertDto) {
        this.description = alertDto.getDescription();
        this.boxIp = alertDto.getBoxIp();
        this.severity = alertDto.getSeverity();
        this.timestamp = alertDto.getTimestamp();
        this.status = AlertStatus.UNASSIGNED;
    }
}
