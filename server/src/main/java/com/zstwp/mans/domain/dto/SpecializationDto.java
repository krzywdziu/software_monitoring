package com.zstwp.mans.domain.dto;

import com.zstwp.mans.domain.database.entities.UserSpecialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SpecializationDto {
    private Long id;
    private UserSpecialization name;
}
