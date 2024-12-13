package com.explorerquest.explorerquest.controller;

import com.explorerquest.explorerquest.dto.UserLoginDto;
import com.explorerquest.explorerquest.dto.UserRegistrationDto;
import com.explorerquest.explorerquest.entity.User;
import com.explorerquest.explorerquest.repository.UserRepository;
import com.explorerquest.explorerquest.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // Endpoint per la registrazione di un nuovo utente
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        if (userRepository.findByUsername(userRegistrationDto.getUsername()).isPresent() ||
                userRepository.findByEmail(userRegistrationDto.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body(Map.of("message", "Username or email already exists"));
        }

        User user = User.builder()
                .username(userRegistrationDto.getUsername())
                .email(userRegistrationDto.getEmail())
                .password(passwordEncoder.encode(userRegistrationDto.getPassword()))
                .firstName(userRegistrationDto.getFirstName())
                .lastName(userRegistrationDto.getLastName())
                .profileImageUrl(userRegistrationDto.getProfileImageUrl())
                .build();

        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "User registered successfully!"));
    }

    // Endpoint per il login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto loginRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(loginRequest.getUsername());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid username or password"));
        }

        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }

    // Endpoint per ottenere i dati dell'utente loggato
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("message", "Missing or invalid token"));
        }

        String token = authHeader.substring(7); // Rimuove "Bearer "
        String username = jwtUtil.extractUsername(token);
        if (username == null) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid token"));
        }

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }

        User user = optionalUser.get();

        // Crea la mappa manualmente per gestire valori null
        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("id", user.getId());
        userDetails.put("username", user.getUsername() != null ? user.getUsername() : "");
        userDetails.put("email", user.getEmail() != null ? user.getEmail() : "");
        userDetails.put("firstName", user.getFirstName() != null ? user.getFirstName() : "");
        userDetails.put("lastName", user.getLastName() != null ? user.getLastName() : "");
        userDetails.put("profileImageUrl", user.getProfileImageUrl() != null ? user.getProfileImageUrl() : "");
        userDetails.put("bio", user.getBio() != null ? user.getBio() : "");
        userDetails.put("dateOfBirth", user.getDateOfBirth() != null ? user.getDateOfBirth().toString() : "");

        return ResponseEntity.ok(userDetails);
    }

    // Endpoint per ottenere la lista di tutti gli utenti
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // Endpoint per eliminare un utente
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }

        userRepository.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }

    // Endpoint per modificare un utente
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }

        User existingUser = userOptional.get();

        // Aggiorna i campi modificabili
        if (updatedUser.getUsername() != null) existingUser.setUsername(updatedUser.getUsername());
        if (updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        if (updatedUser.getFirstName() != null) existingUser.setFirstName(updatedUser.getFirstName());
        if (updatedUser.getLastName() != null) existingUser.setLastName(updatedUser.getLastName());
        if (updatedUser.getProfileImageUrl() != null) existingUser.setProfileImageUrl(updatedUser.getProfileImageUrl());
        if (updatedUser.getBio() != null) existingUser.setBio(updatedUser.getBio());
        if (updatedUser.getDateOfBirth() != null) existingUser.setDateOfBirth(updatedUser.getDateOfBirth());

        userRepository.save(existingUser);

        return ResponseEntity.ok(existingUser);
    }
}
