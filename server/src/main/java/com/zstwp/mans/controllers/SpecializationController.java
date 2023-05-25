package com.zstwp.mans.controllers;


import com.zstwp.mans.database.entities.Specialization;
import com.zstwp.mans.services.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/specialisation")
public class SpecializationController {

    private final SpecializationService specializationService;

    @GetMapping("/all")
    public List<Specialization> getAllSpecializations() {
        return specializationService.getAllSpecializations();
    }
}
