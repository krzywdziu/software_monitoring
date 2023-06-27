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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Value("${demo.email}")
    private String demoEmail;

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

            List<String> exampleDescriptions = List.of(
                    "constraint 'uk_r43af9ap4edm43mmtq01oddj6' of relation 'sers' does not exist",
                    "rsvp_flow_stateMachine: state SESSIONED does not expect event PATHTEAR",
                    "mailslot_create: setsockopt(MCAST_ADD) failed - EDC8116I Address not available.",
                    "Connection limit of 500 inbound connections reached for host 192.168.42.192",
                    "Unable to categorize 'example.com' by Kerio Web Filter. DNS response 'FAILURE: Invalid authorization' to query 'example.com.f836.ko-34554.v3.url.zvelo.com' is invalid",
                    "License update failed: Automatic license update failed. User interaction is required by registration server",
                    "Thread: Critical error detected c0000374",
                    "最近在探索应用webassembly技术，将之前项目的Typescript写的一个模块改为c/c++实现",
                    "Caused by: org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: com.example: could not initialize proxy - no Session",
                    "Error Handling via Dead Letter Queue in Apache Kafka",
                    "The remote server returned an error (500) Internal Server Error",
                    "VPN client error: Error 912",
                    "EvaluatorTimestampFormatPatternDuplicateFields:887",
                    "NetworkManager: definition eth0 is not for us (backend 1): eth0 not found in {}",
                    "src/parse.c:1120:handle_gateway4: assertion failed (scalar(node) == cur_netdef->gateway4): ('correct_gateway' == 'some_random_ip')",
                    "[ERRO][UPF][Main] UPF Cli Run Error: open Gtp5g: open link: create: operation not supported",
                    "free5GC does not work after heal operation because the UPF is not reconfigured and the SMF is not restarted",
                    "‘System.Threading.ThreadAbortException: Thread was being aborted’ while using Response.Redirect"
            );

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
                    .email(demoEmail)
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
                        .description(exampleDescriptions.get(i% exampleDescriptions.size()))
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
