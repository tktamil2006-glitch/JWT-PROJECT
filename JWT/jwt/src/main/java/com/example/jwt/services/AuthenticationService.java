package com.example.jwt.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.jwt.dto.AuthenticationResponse;
import com.example.jwt.entity.user;
import com.example.jwt.repository.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServices jwtServices;
    private final AuthenticationManager authenticationManager;

    // Explicit constructor injection (No Lombok needed)
    public AuthenticationService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtServices jwtServices,
        AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtServices = jwtServices;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(AuthenticationResponse request)
    {
        user userObj = user.builder()
            .name(request.getName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .build();
        userRepository.save(userObj);
        String jwtToken = jwtServices.generateToken(userObj);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationResponse request)
    {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        user userObj = userRepository.findByEmail(request.getEmail())
            .orElseThrow();
        String jwtToken = jwtServices.generateToken(userObj);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }
}
