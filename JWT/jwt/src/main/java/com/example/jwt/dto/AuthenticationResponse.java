package com.example.jwt.dto;

import com.example.jwt.entity.Role;

public class AuthenticationResponse 
{
    private String name;
    private String email;
    private String password;
    private String token;
    private Role role;    

    // Constructors
    public AuthenticationResponse() {}

    public AuthenticationResponse(String name, String email, String password, String token, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
        this.role = role;
    }

    // Manual Builder Pattern to support Lombok's builder syntax without Lombok
    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }

    public static class AuthenticationResponseBuilder {
        private String name;
        private String email;
        private String password;
        private String token;
        private Role role;

        public AuthenticationResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AuthenticationResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        public AuthenticationResponseBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponseBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(name, email, password, token, role);
        }
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
