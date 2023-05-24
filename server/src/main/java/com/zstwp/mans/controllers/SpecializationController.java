package com.zstwp.mans.controllers;


import com.zstwp.mans.database.entities.Specialization;
import com.zstwp.mans.services.SpecialisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/specialisation")
public class SpecializationController {

    private final SpecialisationService specialisationService;

    @GetMapping("/all")
    public List<Specialization> getAllSpecialisations() {
        return specialisationService.getAllSpecialisations();
    }
}
