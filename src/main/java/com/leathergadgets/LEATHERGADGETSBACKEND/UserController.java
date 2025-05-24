package com.leathergadgets.LEATHERGADGETSBACKEND;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody user user) {
        userService.register(user);
        return "user registered successfully";
    }

    @PostMapping("/login")
    public user login(@RequestBody user user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    @PostMapping("/preferences")
    public ResponseEntity<String> savePreferences(@RequestBody PreferenceRequest request) {
        userService.saveUserPreferences(request.getUserId(), request.getPreferences());
        return ResponseEntity.ok("Preferences saved");
    }

    @GetMapping("/{userId}/preferences")
    public ResponseEntity<List<String>> getPreferences(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserPreferences(userId));
    }

    @GetMapping
    public List<user> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/{id}")
    public user updateUser(@PathVariable Long id, @RequestBody user updated) {
        return userService.updateUser(id, updated);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
