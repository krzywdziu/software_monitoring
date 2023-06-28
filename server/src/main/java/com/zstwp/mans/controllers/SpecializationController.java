package com.zstwp.mans.controllers;


import com.zstwp.mans.domain.dto.SpecializationDto;
import com.zstwp.mans.domain.services.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/specialisations")
public class SpecializationController {

    private final SpecializationService specializationService;

    @GetMapping
    public List<SpecializationDto> getAllSpecializations() {
        return specializationService.getAllSpecializations();
    }
}
