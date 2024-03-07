package com.project.spring_project.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String email;

    private String name;

    private String phoneNumber;

    private String password;

    private String roleName;


    // Constructors, getters, and setters
    // Constructor
    public User() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public void setPassword(String password) {
        this.password = password;
    }

}


/*

JSON Format :
{
    "name": "mahesh",
    "phoneNumber": "0123456789",
    "email": "mahesh@gmail.com",
    "password": "mahesh",
    "roleName": "admin"
}
 */