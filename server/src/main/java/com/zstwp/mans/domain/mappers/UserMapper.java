package com.zstwp.mans.domain.mappers;

import com.zstwp.mans.domain.database.entities.User;
import com.zstwp.mans.domain.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SpecializationMapper.class})
public interface UserMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "specializations", target = "specializations", ignore = true)
    UserDto toUserDto(User user);

    List<UserDto> toUserDtos(List<User> users);

    User toUser(UserDto userDto);

//    UserSummaryDto toUserSummary(User user);

//    List<UserSummaryDto> toUserSummaryDtos(List<User> user);

//    @Mapping(target = "userDto.id", source = "id")
//    ProfileDto userToProfileDto(User user);

}
