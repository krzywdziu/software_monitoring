package com.zstwp.mans.controllers;

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
}
