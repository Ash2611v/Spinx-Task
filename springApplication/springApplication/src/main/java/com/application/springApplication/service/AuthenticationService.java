package com.application.springApplication.service;



import com.application.springApplication.model.AuthenticationResponse;
import com.application.springApplication.model.Role;
import com.application.springApplication.model.Token;
import com.application.springApplication.model.User;
import com.application.springApplication.repository.TokenRepository;
import com.application.springApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }


  public void Admin() {
    // Check if admin already exists
    if (userRepository.findByEmail("admin@gmail.com").isPresent()) {
      System.out.println("Admin already exists");
      return;
    }

    User admin = new User();
    admin.setEmail("admin@gmail.com");
    admin.setPassword(passwordEncoder.encode("admin"));
    admin.setUsername("admin");
    admin.setRole(Role.ADMIN);
    admin.setPhoneNumber("9876543201");

    userRepository.save(admin);
    System.out.println("Admin added successfully");
  }

  public AuthenticationResponse register(User request) {
        // check if user already exists. If exists, return an error response
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new AuthenticationResponse(null, "User already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
user.setPhoneNumber(request.getPhoneNumber());
user.setUsername(request.getUsername());

        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        if(user.getUsername() == null) {
            user.setUsername(user.getEmail().split("@")[0]);
        }
        if(user.getPhoneNumber() == null) {
            user.setPhoneNumber("9876543201");
        }

        if(user.getPassword()==null) {
            user.setPassword(passwordEncoder.encode("password"));
        }
        user = userRepository.save(user);

        String jwt = jwtService.generateToken(user);

        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt, "User registration was successful");
    }

    public AuthenticationResponse addUser(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        if(user.getUsername() == null) {
            user.setUsername(user.getEmail().split("@")[0]);
        }
        if(user.getPhoneNumber() == null) {
            user.setPhoneNumber("9876543201");
        }

        if(user.getPassword()==null) {

            user.setPassword(passwordEncoder.encode("password"));
        }else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);

        saveUserToken(jwt, user);
        tokenRepository.findByToken(jwt).ifPresent(t -> {
            t.setLoggedOut(true);
            tokenRepository.save(t);
        });
        return new AuthenticationResponse(jwt, "User registration was successful");
    }
    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt, "User login was successful");
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
        if (validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t -> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }

    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    public void logout(String token) {
        tokenRepository.findByToken(token).ifPresent(t -> {
            t.setLoggedOut(true);
            tokenRepository.save(t);

        });
    }
}
