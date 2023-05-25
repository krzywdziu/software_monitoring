package com.zstwp.mans.config;

import com.zstwp.mans.database.entities.Alert;
import com.zstwp.mans.database.entities.AlertSeverity;
import com.zstwp.mans.database.entities.AlertStatus;
import com.zstwp.mans.database.entities.Specialization;
import com.zstwp.mans.database.entities.User;
import com.zstwp.mans.database.entities.UserRole;
import com.zstwp.mans.database.entities.UserSpecialization;
import com.zstwp.mans.database.repositories.AlertRepository;
import com.zstwp.mans.database.repositories.SpecializationRepository;
import com.zstwp.mans.database.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Configuration
public class InitMockData {

    // generate mock data
    private static final int USER_COUNT = 12;
    private static final int SPECIALIZATION_COUNT = UserSpecialization.values().length;
    private static final int ALERT_COUNT = 22;

    private static final Random PRNG = new Random();

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, AlertRepository alertRepository,
                                        SpecializationRepository specializationRepository) {
        return args -> {

            List<Specialization> specializations = new ArrayList<>();
            List<User> users = new ArrayList<>();
            List<Alert> alerts = new ArrayList<>();

            for(int i = 0; i < SPECIALIZATION_COUNT; i++) {
                Specialization s = new Specialization(UserSpecialization.values()[i]);
                specializations.add(s);
            }

            for(int i = 0; i < USER_COUNT; i++) {
//                String corpEmail = mock.emails().domain("startup.io").val();

                User u = User.builder()
                        .firstName("Robert")
                        .lastName("Makłowicz")
                        .email("example@domain.com")
                        .phoneNumber("123666997")
                        .passwordHash("{noop}pass")
                        .role(UserRole.SERVICEMAN)
                        .specializations(newHashSet(specializations.get(i % SPECIALIZATION_COUNT)))
                        .build();

                users.add(u);
            }

            User test = User.builder()
                    .firstName("Test")
                    .email("test@mail") //@@@@
                    .passwordHash("{noop}pass")
                    .role(UserRole.SERVICEMAN)
                    .specializations(new HashSet<>(specializations))
                    .build();

            User admin = User.builder()
                    .firstName("Admin")
                    .email("admin@email.com")
                    .passwordHash("{noop}pass")
                    .role(UserRole.ADMIN)
                    .build();

            users.add(admin);

            for(int i = 0; i < ALERT_COUNT; i++) {
                Alert a = Alert.builder()
                        .description("Example description")
                        .boxIp("10.0.8.137")
                        .severity(AlertSeverity.values()[i % AlertSeverity.values().length])
                        .timestamp(LocalDateTime.now())
                        .status(AlertStatus.IN_PROGRESS)
                        .user(users.get(PRNG.nextInt(users.size())))
                        .build();
                alerts.add(a);
            }


            users.add(test);

            specializationRepository.saveAll(specializations);
            userRepository.saveAll(users);
            alertRepository.saveAll(alerts);
        };
    }

    public static final <T> Set<T> newHashSet(T... objs) {
        Set<T> set = new HashSet<T>();
        Collections.addAll(set, objs);
        return set;
    }
}
