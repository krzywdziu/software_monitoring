package com.zstwp.mans.controllers;

import com.zstwp.mans.database.entities.User;
import com.zstwp.mans.database.entities.UserSpecialization;
import com.zstwp.mans.dto.UserDto;
import com.zstwp.mans.mappers.UserMapper;
import com.zstwp.mans.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllServicemen() {
        List<User> users = userService.getAllServicemen();
        return userMapper.toUserDtos(users);
    }
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable long id) {
        return userMapper.toUserDto(userService.getUserById(id));
    }

    @GetMapping("/specialisation")
    public List<UserDto> getUsersBySpecialization(@RequestParam UserSpecialization specialization) {
        List<User> users = userService.getUsersBySpecializationName(specialization.toString());
        return userMapper.toUserDtos(users);
    }
}
