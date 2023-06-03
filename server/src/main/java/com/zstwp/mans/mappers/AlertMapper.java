package com.zstwp.mans.mappers;

import com.zstwp.mans.database.entities.Alert;
import com.zstwp.mans.dto.AlertDto;
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