package com.leathergadgets.LEATHERGADGETSBACKEND;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public user register(user user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        if (!user.getPassword().equals(user.getRepeatPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRepeatPassword(null);

        return userRepository.save(user);
    }

    public user login(String email, String password) {
        user user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }

    public void saveUserPreferences(Long userId, List<String> preferences) {
        user user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPreferences(preferences);
        userRepository.save(user);
    }

    public List<String> getUserPreferences(Long userId) {
        return userRepository.findById(userId)
                .map(user::getPreferences)
                .orElse(Collections.emptyList());
    }

    public user updateUser(Long id, user updatedUser) {
        user existing = userRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(updatedUser.getName());
            existing.setMobile(updatedUser.getMobile());
            existing.setEmail(updatedUser.getEmail());
            return userRepository.save(existing);
        }
        return null;
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
