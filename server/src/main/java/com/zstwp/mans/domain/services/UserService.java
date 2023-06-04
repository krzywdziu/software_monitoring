package com.zstwp.mans.domain.services;

import com.zstwp.mans.domain.database.entities.User;
import com.zstwp.mans.domain.database.entities.UserRole;
import com.zstwp.mans.domain.database.repositories.UserRepository;
import com.zstwp.mans.domain.services.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllServicemen() {
        return userRepository.findAllByRole(UserRole.SERVICEMAN);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email " + email));
    }

    public List<User> getUsersBySpecializationName(String specialisation) {
        return userRepository.findUsersBySpecializationName(specialisation);
    }

    public User getUserBySpecializationAndFewestAlerts(String specialisation) {
        return userRepository.findUserBySpecializationAndWithFewestAlertsAssigned(specialisation)
                .orElseThrow(() -> new UserNotFoundException("User not found with specialization " + specialisation));
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
