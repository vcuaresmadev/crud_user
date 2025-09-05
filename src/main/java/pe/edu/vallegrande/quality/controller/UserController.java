
package pe.edu.vallegrande.quality.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.quality.dto.ApiResponse;
import pe.edu.vallegrande.quality.dto.UserRequest;
import pe.edu.vallegrande.quality.model.User;
import pe.edu.vallegrande.quality.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        logger.info("Getting all users");
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success(users));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody UserRequest userRequest) {
        logger.info("Creating new user with name: {}", userRequest.getName());
        
        User user = new User(null, userRequest.getName(), userRequest.getEmail(), userRequest.getAge());
        User createdUser = userService.createUser(user);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User created successfully", createdUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable String id) {
        logger.info("Getting user by id: {}", id);
        User user = userService.findUserById(id);
        return ResponseEntity.ok(ApiResponse.success(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String id) {
        logger.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));
    }
}
