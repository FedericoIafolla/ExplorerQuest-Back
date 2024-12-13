package com.explorerquest.explorerquest.controller;

import com.explorerquest.explorerquest.dto.UserProfileUpdateDto;
import com.explorerquest.explorerquest.entity.User;
import com.explorerquest.explorerquest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PutMapping("/{id}/profile")
    public ResponseEntity<?> updateUserProfile(@PathVariable Long id, @RequestBody UserProfileUpdateDto profileUpdateDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        User user = optionalUser.get();
        user.setFirstName(profileUpdateDto.getFirstName());
        user.setLastName(profileUpdateDto.getLastName());
        user.setBio(profileUpdateDto.getBio());
        user.setProfileImageUrl(profileUpdateDto.getProfileImageUrl());
        user.setDateOfBirth(profileUpdateDto.getDateOfBirth());

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
}
