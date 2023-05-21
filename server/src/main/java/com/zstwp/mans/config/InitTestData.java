package com.zstwp.mans.config;

import com.zstwp.mans.database.entities.Alert;
import com.zstwp.mans.database.entities.AlertSeverity;
import com.zstwp.mans.database.entities.AlertStatus;
import com.zstwp.mans.database.entities.Specialization;
import com.zstwp.mans.database.entities.User;
import com.zstwp.mans.database.repositories.AlertRepository;
import com.zstwp.mans.database.repositories.SpecializationRepository;
import com.zstwp.mans.database.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class InitTestData {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, AlertRepository alertRepository,
                                        SpecializationRepository specializationRepository) {
        return args -> {
            Alert a1 = Alert.builder()
                    .description("Docker container down")
                    .boxIp("10.0.0.17")
                    .severity(AlertSeverity.ERROR)
                    .timestamp(LocalDateTime.now())
                    .status(AlertStatus.IN_PROGRESS)
                    .build();

            Alert a2 = Alert.builder()
                    .description("Server down")
                    .boxIp("10.0.0.18")
                    .severity(AlertSeverity.CRITICAL)
                    .timestamp(LocalDateTime.now())
                    .status(AlertStatus.WONT_FIX)
                    .build();

            Alert a3 = Alert.builder()
                    .description("Log rotation needed")
                    .boxIp("10.0.0.19")
                    .severity(AlertSeverity.NOTICE)
                    .timestamp(LocalDateTime.now())
                    .status(AlertStatus.IN_PROGRESS)
                    .build();

            Alert a4 = Alert.builder()
                    .description("Load average too high")
                    .boxIp("10.0.0.20")
                    .severity(AlertSeverity.WARNING)
                    .timestamp(LocalDateTime.now())
                    .status(AlertStatus.UNASSIGNED)
                    .build();

            Alert a5 = Alert.builder()
                    .description("Network connection error")
                    .boxIp("10.0.0.21")
                    .severity(AlertSeverity.ERROR)
                    .timestamp(LocalDateTime.now())
                    .status(AlertStatus.UNASSIGNED)
                    .build();

            alertRepository.saveAll(
                    List.of(a1, a2, a3, a4, a5)
            );

            User u1 = User.builder()
                    .firstName("Robert")
                    .lastName("Makłowicz")
                    .email("robert@gmail.com")
                    .phoneNumber("123456789")
                    .passwordHash("{noop}pass")
                    .build();

            User u2 = User.builder()
                    .firstName("Karol")
                    .lastName("Okrasa")
                    .email("karol@gmail.com")
                    .phoneNumber("987654321")
                    .passwordHash("{noop}pass")
                    .build();

            User u3 = User.builder()
                    .firstName("Jan")
                    .lastName("Sobieski")
                    .email("jan3@gmail.com")
                    .phoneNumber("111222333")
                    .passwordHash("{noop}pass")
                    .build();

            userRepository.saveAll(
                    List.of(u1, u2, u3)
            );

            Specialization s1 = Specialization.builder()
                    .name("Docker")
                    .users(newHashSet(u1))
                    .build();
            Specialization s2 = Specialization.builder()
                    .name("Gotowanie")
                    .users(newHashSet(u1, u2))
                    .build();
            Specialization s3 = Specialization.builder()
                    .name("Leadership")
                    .users(newHashSet(u3))
                    .build();

            specializationRepository.saveAll(
                    List.of(s1, s2, s3)
            );
        };
    }

    public static final <T> Set<T> newHashSet(T... objs) {
        Set<T> set = new HashSet<T>();
        Collections.addAll(set, objs);
        return set;
    }
}
