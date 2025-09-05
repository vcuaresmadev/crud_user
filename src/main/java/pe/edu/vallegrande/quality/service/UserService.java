
package pe.edu.vallegrande.quality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.quality.model.User;
import pe.edu.vallegrande.quality.repository.UserRepository;
import pe.edu.vallegrande.quality.service.exception.UserNotFoundException;
import pe.edu.vallegrande.quality.service.exception.UserValidationException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public User createUser(User user) {
        validateUser(user);

        if (user.getId() == null || user.getId().trim().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        }

        if (user.getAge() == null) {
            user.setAge(0);
        }

        return userRepository.save(user);
    }

    public User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public boolean deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return userRepository.deleteById(id);
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new UserValidationException("User cannot be null");
        }

        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new UserValidationException("User name is required");
        }

        if (user.getEmail() == null || !isValidEmail(user.getEmail())) {
            throw new UserValidationException("Valid email is required");
        }

        if (user.getAge() != null && user.getAge() < 0) {
            throw new UserValidationException("Age must be positive");
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}
