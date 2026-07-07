package com.example.jwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.http.ResponseEntity;
import com.example.jwt.dto.AuthenticationResponse;
import com.example.jwt.services.AuthenticationService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController
{
    private final AuthenticationService service;

    // Explicit constructor injection (No Lombok needed)
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
        @ModelAttribute AuthenticationResponse request
    )
    {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            String message = e.getMessage() != null && e.getMessage().contains("Duplicate") 
                ? "Email already exists in database!" 
                : "Registration failed: " + e.getMessage();
            errorResponse.put("message", message);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
        @ModelAttribute AuthenticationResponse request
    )
    {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid email or password!");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }
}