package com.zstwp.mans.services;

import com.zstwp.mans.database.entities.User;
import com.zstwp.mans.database.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository
                .findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("User with given email already exists!");
        }
        userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        boolean exists = userRepository.existsById(userId);

        if (!exists) {
            throw new IllegalStateException(
                    "User with id " + userId + " doesn't exist!"
            );
        }
        userRepository.deleteById(userId);
    }
}
