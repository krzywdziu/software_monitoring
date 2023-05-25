package com.zstwp.mans.services;

import com.zstwp.mans.database.entities.User;
import com.zstwp.mans.database.repositories.UserRepository;
import com.zstwp.mans.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findUserById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        return user.get();
    }

    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with email " + email);
        }
        return user.get();
    }

    public List<User> getUsersBySpecializationName(String specialisation) {
        return userRepository.findUsersBySpecializationName(specialisation);
    }

    public User getUserBySpecializationAndFewestAlerts(String specialisation) {
        Optional<User> user = userRepository.findUserBySpecializationAndWithFewestAlertsAssigned(specialisation);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with specialization " + specialisation);
        }
        return user.get();
    }

//    public void addNewUser(UserModel user) {
//        Optional<UserModel> userOptional = userRepository
//                .findByEmail(user.getEmail());
//        if (userOptional.isPresent()) {
//            throw new IllegalStateException("User with given email already exists!");
//        }
//        userRepository.save(user);
//    }
//
//    public void deleteUser(Long userId) {
//        boolean exists = userRepository.existsById(userId);
//
//        if (!exists) {
//            throw new IllegalStateException(
//                    "User with id " + userId + " doesn't exist!"
//            );
//        }
//        userRepository.deleteById(userId);
//    }
}
