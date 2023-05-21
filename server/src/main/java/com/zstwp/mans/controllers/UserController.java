package com.zstwp.mans.controllers;

import com.zstwp.mans.database.entities.User;
import com.zstwp.mans.dto.UserDto;
import com.zstwp.mans.dto.UserRequest;
import com.zstwp.mans.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        List<UserDto> userDtoList = new ArrayList<>();

        // TODO: mapstruct
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
    public UserDto getUserByEmail(@RequestParam String email) {
        return new UserDto(userService.getUserByEmail(email));
    }

//    @GetMapping("/{specialization}")
//    public List<UserDto> getUsersBySpecializationName(@PathVariable("specialization") String specializationName) {
//
//    }

}
