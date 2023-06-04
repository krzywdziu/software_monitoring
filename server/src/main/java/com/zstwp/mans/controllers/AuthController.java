//package com.zstwp.mans.controllers;
//
//import com.zstwp.mans.dto.LoginDto;
//import com.zstwp.mans.models.LoginRequest;
//import com.zstwp.mans.services.TokenService;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//public class AuthController {
//
//    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
//
//    private final TokenService tokenService;
//
//    private final AuthenticationManager authenticationManager;
//
//    @PostMapping("/token")
//    public LoginDto token(@RequestBody LoginRequest userLogin) {
//
//        LOG.debug("Token requested for user: '{}'", userLogin);
//
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(
//                        userLogin.username(), userLogin.password())
//                );
//
//        long id = 1L;
//        String token = tokenService.generateToken(authentication);
//
//        LOG.debug("Token granted {}", token);
//
//        return new LoginDto(id, token);
//    }
//}
