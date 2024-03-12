package com.application.springApplication.service;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.application.springApplication.model.Role;
import com.application.springApplication.model.User;
import com.application.springApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository repository;


    public UserDetailsServiceImp(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public ArrayList<UserDTO> getAllUsers() {
        ArrayList<UserDTO> users = new ArrayList<>();
        repository.findAll().forEach(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTO.setRole(user.getRole().name());
            users.add(userDTO);
        });

        return users;
    }

    public void updateUser(String email, User user) {
repository.findByEmail(email).ifPresent(u -> {
    u.setUsername(user.getUsername());
    u.setPhoneNumber(user.getPhoneNumber());
    u.setEmail(user.getEmail());
    u.setRole(user.getRole());
    repository.save(u);
        });
    }

  public void deleteUser(String email) {
    repository.findByEmail(email).ifPresent(u -> {
      if(Objects.equals(email, "admin@gmail.com")){
        return;
      }
      repository.delete(u);
    });

  }

    public static class UserDTO {
        private String name;
        private String email;

        public String getUsername() {
            return name;
        }

        public void setUsername(String username) {
            this.name = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getRole() {
            return roleName;
        }

        public void setRole(String role) {
            this.roleName = role;
        }

        private String phoneNumber;
        private String roleName;

        // Constructor, getters, and setters
    }
}



