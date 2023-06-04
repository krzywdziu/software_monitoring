package com.zstwp.mans.domain.mappers;

import com.zstwp.mans.domain.database.entities.Alert;
import com.zstwp.mans.domain.dto.AlertDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AlertMapper {

    @Mapping(target = "userDto", source = "user")
    @Mapping(source = "id", target = "id")
    AlertDto toAlertDto(Alert alert);

//    @Mapping(target = "userDto", source = "user")
    List<AlertDto> toAlertDtos(List<Alert> alerts);
}