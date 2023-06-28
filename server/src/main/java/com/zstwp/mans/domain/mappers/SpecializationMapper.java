package com.zstwp.mans.domain.mappers;

import com.zstwp.mans.domain.database.entities.Specialization;
import com.zstwp.mans.domain.dto.SpecializationDto;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SpecializationMapper {
    SpecializationDto toDto(Specialization specialization);
    Set<SpecializationDto> toDtos(Set<Specialization> specializations);
}
