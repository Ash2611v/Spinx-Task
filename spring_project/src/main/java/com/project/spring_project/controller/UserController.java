package com.project.spring_project.controller;


import com.project.spring_project.entity.LoginRequest;
import com.project.spring_project.entity.User;
import com.project.spring_project.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers(){
     userService.initRolesAndUsers();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user){


        return userService.registerNewUser(user);
    }

    @GetMapping({"/getAllUsers"})
    public ResponseEntity<User[]> getAllUsers() {
        User[] users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Validate user credentials
        if (userService.validateCredentials(email, password)) {
            // Credentials are valid
            return new LoginResponse("Login successful");
        } else {
            // Credentials are invalid
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }
    }


    // DELETE endpoint to delete a user by ID
    @DeleteMapping("deleteUser/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.ok("User deleted successfully");
    }

    // PUT endpoint to update a user by ID
    @PutMapping("updateUser/{email}")
    public ResponseEntity<User> updateUser(@PathVariable String email, @RequestBody User updatedUser) {
        User user = userService.updateUser(email, updatedUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public static class LoginResponse {
        private String message;
        public LoginResponse(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}

