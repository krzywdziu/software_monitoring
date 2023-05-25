package com.zstwp.mans.services;

import com.zstwp.mans.database.entities.Specialization;
import com.zstwp.mans.database.repositories.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }
}
