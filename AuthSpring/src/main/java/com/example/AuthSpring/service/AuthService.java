package com.example.AuthSpring.service;

import com.example.AuthSpring.model.User;
import com.example.AuthSpring.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User register(String username, String email, String password) {
        Optional<User> existsEmail = authRepository.findByEmail(email);
        Optional<User> existingUsername = authRepository.findByUsername(username);
        if (existsEmail.isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        } else if (existingUsername.isPresent()) {
            throw new IllegalArgumentException("Username already in use");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return authRepository.save(user);
    }


    public boolean authenticate(String username, String password){
        return authRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }
}
