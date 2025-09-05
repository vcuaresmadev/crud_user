package pe.edu.vallegrande.quality.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pe.edu.vallegrande.quality.dto.UserRequest;
import pe.edu.vallegrande.quality.model.User;
import pe.edu.vallegrande.quality.service.UserService;
import pe.edu.vallegrande.quality.service.exception.UserNotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUsers_ShouldReturnUserList() throws Exception {
        // Given
        List<User> users = Arrays.asList(
                new User("1", "John", "john@example.com", 25),
                new User("2", "Jane", "jane@example.com", 30)
        );
        when(userService.getAllUsers()).thenReturn(users);

        // When & Then
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(2));
    }

    @Test
    void createUser_WithValidData_ShouldReturnCreatedUser() throws Exception {
        // Given
        UserRequest request = new UserRequest("John", "john@example.com", 25);
        User createdUser = new User("1", "John", "john@example.com", 25);
        when(userService.createUser(any(User.class))).thenReturn(createdUser);

        // When & Then
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("John"));
    }

    @Test
    void createUser_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        // Given
        UserRequest request = new UserRequest("", "invalid-email", -1);

        // When & Then
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUserById_WithExistingId_ShouldReturnUser() throws Exception {
        // Given
        User user = new User("1", "John", "john@example.com", 25);
        when(userService.findUserById("1")).thenReturn(user);

        // When & Then
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("John"));
    }

    @Test
    void getUserById_WithNonExistingId_ShouldReturnNotFound() throws Exception {
        // Given
        when(userService.findUserById("999")).thenThrow(new UserNotFoundException("User not found"));

        // When & Then
        mockMvc.perform(get("/api/users/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void deleteUser_WithExistingId_ShouldReturnSuccess() throws Exception {
        // Given
        when(userService.deleteUser("1")).thenReturn(true);

        // When & Then
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}