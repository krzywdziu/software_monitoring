package com.zstwp.mans.config;

import com.zstwp.mans.database.entities.User;
import com.zstwp.mans.database.repositories.UserRepository;
import com.zstwp.mans.database.entities.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User s1 = new User("s1@example.com", "{noop}pass", UserRole.SERVICEMAN );
            User s2 = new User("s2@example.com", "{noop}pass", UserRole.SERVICEMAN);
            User s3 = new User("s3@example.com", "{noop}pass", UserRole.SERVICEMAN);
            User a1 = new User("a1@example.com", "{noop}pass", UserRole.ADMIN );
            User a2 = new User("a2@example.com", "{noop}pass", UserRole.ADMIN);
       repository.saveAll(
                    List.of(s1, s2, s3, a1, a2)
            );
        };
    }

}
