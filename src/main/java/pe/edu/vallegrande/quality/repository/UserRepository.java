
package pe.edu.vallegrande.quality.repository;

import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.quality.model.User;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        users.put(user.getId(), user);
        return user;
    }

    public Optional<User> findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(users.get(id));
    }

    public boolean deleteById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        return users.remove(id) != null;
    }

    public boolean existsById(String id) {
        return id != null && users.containsKey(id);
    }

    public long count() {
        return users.size();
    }
}
