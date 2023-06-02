package com.zstwp.mans.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/me")
    public String home(Principal principal) {
        return "hello " + principal.getName() + "!";
    }

    @PreAuthorize("hasAuthority('SCOPE_read')")
    @GetMapping("/hello")
    public String secure() {
        return "Hello! Server is running";
    }
}
