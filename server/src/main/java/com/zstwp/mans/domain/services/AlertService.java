package com.zstwp.mans.domain.services;

import com.zstwp.mans.domain.database.entities.Alert;
import com.zstwp.mans.domain.database.entities.AlertSeverity;
import com.zstwp.mans.domain.database.entities.AlertStatus;
import com.zstwp.mans.domain.database.entities.Specialization;
import com.zstwp.mans.domain.database.entities.User;
import com.zstwp.mans.domain.database.repositories.AlertRepository;
import com.zstwp.mans.domain.database.repositories.SpecializationRepository;
import com.zstwp.mans.domain.database.repositories.UserRepository;
import com.zstwp.mans.domain.dto.AlertDto;
import com.zstwp.mans.domain.mappers.AlertMapper;
import com.zstwp.mans.domain.mappers.SpecializationMapper;
import com.zstwp.mans.domain.mappers.UserMapper;
import com.zstwp.mans.domain.services.exceptions.AlertNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final AlertMapper alertMapper;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final SpecializationRepository specializationRepository;
    private final SpecializationMapper specializationMapper;

    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public Alert getAlertById(long id) {
        return alertRepository.findAlertById(id)
                .orElseThrow(() -> new AlertNotFoundException("Alert not found with id " + id));
    }

    public AlertDto getAlertDtoById(long id) {
        Alert alert = getAlertById(id);
        AlertDto alertDto = alertMapper.toAlertDto(alert);
        Set<Specialization> specializations = specializationRepository.findAllByUsers(alert.getUser());
        alertDto.getUserDto().setSpecializations(specializationMapper.toDtos(specializations));
        return alertDto;
    }

    public List<Alert> getAlertsBySeverity(AlertSeverity severity) {
        return alertRepository.findAllBySeverity(severity);
    }

    public List<Alert> getAlertsByBoxIp(String ip) {
        return alertRepository.findAllByBoxIp(ip);
    }

    public List<Alert> getAlertsByStatus(AlertStatus status) {
        return alertRepository.findAllByStatus(status);
    }

    public List<Alert> getAlertsByUserId(long id) {
        return alertRepository.findAllByUserId(id);
    }

    public Integer updateAlertStatus(long id, AlertStatus status) {
        return alertRepository.updateAlertStatus(id, status);
    }

    public Integer assignAlertToUser(long alertId, long userId) {
        var user = userService.getUserById(userId);
        var alert = getAlertById(alertId);
        emailService.sendEmail(user.getEmail(), " New alert!", alert.getDescription());
        return alertRepository.assignAlertToUser(alertId, userService.getUserById(userId));
    }

    public void addNewAlert(AlertDto alert) {
//        String/AlertDto -> Alert
//        Alert alert = new Alert(alertDto);

        // check if alert already exists (in the db)
        if (true) {
            User user = userRepository.getReferenceById(alert.getUserDto().getId());
            Alert alertEntity = alertMapper.toAlertEntity(alert);
            alertEntity.setUser(user);
            alertRepository.save(alertEntity);
        }

    }

    public int countAllAlerts() {
        return (int) alertRepository.count();
    }

}
