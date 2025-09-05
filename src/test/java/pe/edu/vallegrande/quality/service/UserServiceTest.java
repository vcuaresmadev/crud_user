package pe.edu.vallegrande.quality.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.vallegrande.quality.model.User;
import pe.edu.vallegrande.quality.repository.UserRepository;
import pe.edu.vallegrande.quality.service.exception.UserNotFoundException;
import pe.edu.vallegrande.quality.service.exception.UserValidationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User("1", "John Doe", "john@example.com", 25);
    }

    @Test
    void getAllUsers_ShouldReturnSortedUsers() {
        // Given
        List<User> users = Arrays.asList(
                new User("2", "Bob", "bob@example.com", 30),
                new User("1", "Alice", "alice@example.com", 25)
        );
        when(userRepository.findAll()).thenReturn(users);

        // When
        List<User> result = userService.getAllUsers();

        // Then
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Bob", result.get(1).getName());
    }

    @Test
    void createUser_WithValidUser_ShouldReturnCreatedUser() {
        // Given
        User userToCreate = new User(null, "John", "john@example.com", 25);
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // When
        User result = userService.createUser(userToCreate);

        // Then
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createUser_WithNullUser_ShouldThrowException() {
        // When & Then
        assertThrows(UserValidationException.class, () -> userService.createUser(null));
    }

    @Test
    void createUser_WithEmptyName_ShouldThrowException() {
        // Given
        User invalidUser = new User(null, "", "john@example.com", 25);

        // When & Then
        assertThrows(UserValidationException.class, () -> userService.createUser(invalidUser));
    }

    @Test
    void createUser_WithInvalidEmail_ShouldThrowException() {
        // Given
        User invalidUser = new User(null, "John", "invalid-email", 25);

        // When & Then
        assertThrows(UserValidationException.class, () -> userService.createUser(invalidUser));
    }

    @Test
    void findUserById_WithExistingId_ShouldReturnUser() {
        // Given
        when(userRepository.findById("1")).thenReturn(Optional.of(testUser));

        // When
        User result = userService.findUserById("1");

        // Then
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void findUserById_WithNonExistingId_ShouldThrowException() {
        // Given
        when(userRepository.findById("999")).thenReturn(Optional.empty());

        // When & Then
        assertThrows(UserNotFoundException.class, () -> userService.findUserById("999"));
    }

    @Test
    void deleteUser_WithExistingId_ShouldReturnTrue() {
        // Given
        when(userRepository.existsById("1")).thenReturn(true);
        when(userRepository.deleteById("1")).thenReturn(true);

        // When
        boolean result = userService.deleteUser("1");

        // Then
        assertTrue(result);
        verify(userRepository).deleteById("1");
    }

    @Test
    void deleteUser_WithNonExistingId_ShouldThrowException() {
        // Given
        when(userRepository.existsById("999")).thenReturn(false);

        // When & Then
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser("999"));
    }
}