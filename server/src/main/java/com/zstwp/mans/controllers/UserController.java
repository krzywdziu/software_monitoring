package com.zstwp.mans.controllers;

import com.zstwp.mans.database.entities.User;
import com.zstwp.mans.database.entities.UserSpecialization;
import com.zstwp.mans.dto.UserDto;
import com.zstwp.mans.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        List<UserDto> userDtoList = new ArrayList<>();

        userList.stream().forEach(user -> {
            userDtoList.add(new UserDto(user));
        });

        return userDtoList;
    }
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable long id) {
        return new UserDto(userService.getUserById(id));
    }

    @GetMapping
    public List<UserDto> getUsersBySpecialization(@RequestParam UserSpecialization specialization) {
        List<User> userList = userService.getUsersBySpecializationName(specialization.toString());
        List<UserDto> userDtoList = new ArrayList<>();

        userList.stream().forEach(user -> {
            userDtoList.add(new UserDto(user));
        });

        return userDtoList;
    }
}
