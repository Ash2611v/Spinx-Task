package com.application.springApplication.controller;

import com.application.springApplication.model.User;
import com.application.springApplication.service.AuthenticationService;
import com.application.springApplication.service.UserDetailsServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@RestController
public class DemoController {
    UserDetailsServiceImp userDetailsServiceImp;

    public DemoController(UserDetailsServiceImp userDetailsServiceImp) {
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello from secured url");
    }

    @GetMapping("/admin_only")
    public ArrayList<UserDetailsServiceImp.UserDTO> getAllUsers() {
        return this.userDetailsServiceImp.getAllUsers();
    }

    @PutMapping("/update_user/{email}")
    public ResponseEntity<String> updateUser(@PathVariable String email , @RequestBody User user) {
        this.userDetailsServiceImp.updateUser(email, user);

        return ResponseEntity.ok("User updated successfully");
    }



//    @GetMapping("/user_only")
//    public UserDetails getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//            return (UserDetails) authentication.getPrincipal();
//        }
//        return null;
//    }



    @DeleteMapping({ "/delete_user/{email}"})
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        this.userDetailsServiceImp.deleteUser(email);

        return ResponseEntity.ok("User deleted successfully");
    }


}
