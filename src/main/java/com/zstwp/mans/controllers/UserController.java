package com.zstwp.mans.controllers;

import com.zstwp.mans.database.entities.User;
import com.zstwp.mans.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = {"/user/{userId}", "/user"})
public class UserController {

    @NonNull
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(@PathVariable(value = "userId", required = false) Integer userId) {
        if(userId == null){
            return userService.getUsers();
        } else{
            return userService.getUsers(); //ma byc getUserByID
        }

    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
    }
}
