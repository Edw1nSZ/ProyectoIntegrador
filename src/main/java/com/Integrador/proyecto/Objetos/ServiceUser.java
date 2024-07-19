package com.Integrador.proyecto.Objetos;

import com.Integrador.proyecto.Interfaces.UserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceUser implements UserService {
    private final Map<Long, User> userMap = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public User createUser(User user) {
        user.setId(currentId++);
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User updateUser(Long id, User user) {
        if (userMap.containsKey(id)) {
            user.setId(id);
            userMap.put(id, user);
            return user;
        }
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        return userMap.remove(id) != null;
    }
}
