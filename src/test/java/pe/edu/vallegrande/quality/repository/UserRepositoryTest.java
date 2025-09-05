package pe.edu.vallegrande.quality.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pe.edu.vallegrande.quality.model.User;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository userRepository;
    private User testUser;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        testUser = new User("1", "John Doe", "john@example.com", 25);
    }

    @Test
    void save_ShouldStoreUser() {
        // When
        User savedUser = userRepository.save(testUser);

        // Then
        assertNotNull(savedUser);
        assertEquals(testUser.getId(), savedUser.getId());
        assertEquals(1, userRepository.count());
    }

    @Test
    void save_WithNullUser_ShouldThrowException() {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> userRepository.save(null));
    }

    @Test
    void findById_WithExistingId_ShouldReturnUser() {
        // Given
        userRepository.save(testUser);

        // When
        Optional<User> result = userRepository.findById("1");

        // Then
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    void findById_WithNonExistingId_ShouldReturnEmpty() {
        // When
        Optional<User> result = userRepository.findById("999");

        // Then
        assertFalse(result.isPresent());
    }

    @Test
    void findById_WithNullId_ShouldReturnEmpty() {
        // When
        Optional<User> result = userRepository.findById(null);

        // Then
        assertFalse(result.isPresent());
    }

    @Test
    void findAll_ShouldReturnAllUsers() {
        // Given
        User user2 = new User("2", "Jane Doe", "jane@example.com", 30);
        userRepository.save(testUser);
        userRepository.save(user2);

        // When
        List<User> result = userRepository.findAll();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void deleteById_WithExistingId_ShouldReturnTrue() {
        // Given
        userRepository.save(testUser);

        // When
        boolean result = userRepository.deleteById("1");

        // Then
        assertTrue(result);
        assertEquals(0, userRepository.count());
    }

    @Test
    void deleteById_WithNonExistingId_ShouldReturnFalse() {
        // When
        boolean result = userRepository.deleteById("999");

        // Then
        assertFalse(result);
    }

    @Test
    void existsById_WithExistingId_ShouldReturnTrue() {
        // Given
        userRepository.save(testUser);

        // When
        boolean result = userRepository.existsById("1");

        // Then
        assertTrue(result);
    }

    @Test
    void existsById_WithNonExistingId_ShouldReturnFalse() {
        // When
        boolean result = userRepository.existsById("999");

        // Then
        assertFalse(result);
    }
}