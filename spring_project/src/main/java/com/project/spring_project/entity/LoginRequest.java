package com.project.spring_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public class LoginRequest {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;
    private String password;

    // Getters and setters
}