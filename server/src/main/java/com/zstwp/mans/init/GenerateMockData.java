package com.zstwp.mans.init;

import com.zstwp.mans.domain.database.entities.Alert;
import com.zstwp.mans.domain.database.entities.AlertSeverity;
import com.zstwp.mans.domain.database.entities.AlertStatus;
import com.zstwp.mans.domain.database.entities.Specialization;
import com.zstwp.mans.domain.database.entities.User;
import com.zstwp.mans.domain.database.entities.UserRole;
import com.zstwp.mans.domain.database.entities.UserSpecialization;
import com.zstwp.mans.domain.database.repositories.AlertRepository;
import com.zstwp.mans.domain.database.repositories.SpecializationRepository;
import com.zstwp.mans.domain.database.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class GenerateMockData {

    private static final int SERVICEMEN_COUNT = 12;
    private static final int SPECIALIZATION_COUNT = UserSpecialization.values().length;
    private static final int ALERT_COUNT = 22;

    private final PasswordEncoder passwordEncoder;

    private static final Random PRNG = new Random();

    private Set<Specialization> getNRandSpecializations(List<Specialization> specializations) {
        int size = PRNG.nextInt(1,5);
        Set<Specialization> mySet = new HashSet<>();

        for (int i = 0; i < size; i++) {
            mySet.add(specializations.get(PRNG.nextInt(specializations.size())));
        }

        return mySet;
    }

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

            for(int i = 0; i < SERVICEMEN_COUNT; i++) {
                User u = User.builder()
                        .firstName("Robert")
                        .lastName("Makłowicz")
                        .email("user" + (i+1) + "@example.com")
                        .phoneNumber("123666997")
                        .passwordHash(passwordEncoder.encode("test"))
                        .role(UserRole.SERVICEMAN)
                        .specializations(
                                getNRandSpecializations(specializations)
                        )
                        .build();

                users.add(u);
            }

            User demo = User.builder()
                    .firstName("demo")
                    .lastName("demo")
                    .email("panLimonka965@gmail.com")
                    .passwordHash(passwordEncoder.encode("test"))
                    .role(UserRole.SERVICEMAN)
                    .specializations(new HashSet<>(specializations))
                    .build();

            User test = User.builder()
                    .firstName("test")
                    .email("test@example.com") //@@@@
                    .passwordHash(passwordEncoder.encode("test"))
                    .role(UserRole.SERVICEMAN)
                    .specializations(new HashSet<>(specializations)) //all
                    .build();

            User admin = User.builder()
                    .firstName("admin")
                    .email("admin@example.com")
                    .passwordHash(passwordEncoder.encode("admin"))
                    .role(UserRole.ADMIN)
                    .build();

            for(int i = 0; i < ALERT_COUNT; i++) {
                Alert a = Alert.builder()
                        .description("Example description")
                        .boxIp("10.0.8." + PRNG.nextInt(254))
                        .severity(
                                AlertSeverity.values()[PRNG.nextInt(10) % AlertSeverity.values().length]
                        )
                        .timestamp(LocalDateTime.now())
                        .status(AlertStatus.ASSIGNED)
                        .user(users.get(PRNG.nextInt(users.size())))
                        .build();
                alerts.add(a);
            }

            // no default alerts
            users.add(demo);
            users.add(test);
            users.add(admin);

            specializationRepository.saveAll(specializations);
            userRepository.saveAll(users);
            alertRepository.saveAll(alerts);
        };
    }
}
