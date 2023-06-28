package com.zstwp.mans.domain.services;

import com.zstwp.mans.domain.database.repositories.SpecializationRepository;
import com.zstwp.mans.domain.dto.SpecializationDto;
import com.zstwp.mans.domain.mappers.SpecializationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;
    private final SpecializationMapper specializationMapper;

    public List<SpecializationDto> getAllSpecializations() {
        return specializationRepository.findAll().stream()
                .map(specializationMapper::toDto)
                .toList();
    }
}
