package com.zstwp.mans.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

//    private RSAKey rsaKey;
    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService) {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(authProvider);
    }

    @Bean
    public InMemoryUserDetailsManager user() {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password("{noop}admin")
                        .authorities("read")
                        .build()
        );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setMaxAge(3600L);
                    return config;
                })
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
//                .requestMatchers("/token", "/register")
                .requestMatchers("/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //new session for each request
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();


//        return http
//                .cors().configurationSource(new CorsConfigurationSource() {
//                    @Override
//                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                        CorsConfiguration config = new CorsConfiguration();
//                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//                        config.setAllowedMethods(Collections.singletonList("*"));
//                        config.setAllowCredentials(true);
//                        config.setAllowedHeaders(Collections.singletonList("*"));
//                        config.setMaxAge(3600L);
//                        return config;
//                    }
//                })
//                .and()
//                .csrf(csrf -> csrf.disable())
//                .authorizeRequests(auth -> auth
//                        .requestMatchers("/token", "/hello").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .httpBasic(withDefaults())
//                .exceptionHandling((ex) -> ex
//                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
//                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
//                )
//                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/proxy/**");
    }

//    @Bean
//    public JWKSource<SecurityContext> jwkSource() {
//        rsaKey = Jwks.generateRsa();
//        JWKSet jwkSet = new JWKSet(rsaKey);
//        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
//    }

//    @Bean
//    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwks) {
//        return new NimbusJwtEncoder(jwks);
//    }

//    @Bean
//    JwtDecoder jwtDecoder() throws JOSEException {
//        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET","POST","PUT"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }


}
