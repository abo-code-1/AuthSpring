package com.example.AuthSpring.controller;


import com.example.AuthSpring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String authPage() {
        return "auth";
    }


    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request){
        String username = request.get("username");
        String email = request.get("email");
        String password = request.get("password");
        try{
            return ResponseEntity.ok(authService.register(username, email, password));

        }catch(IllegalStateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request){
        String username = request.get("username");
        String password = request.get("password");
        boolean isAuthenticated = authService.authenticate(username, password);
        if(isAuthenticated){
            return ResponseEntity.ok("Login successful");
        }
        else{
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }
}
