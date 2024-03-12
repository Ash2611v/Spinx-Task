package com.application.springApplication;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.application.springApplication.model.Role;
import com.application.springApplication.model.User;
import com.application.springApplication.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

  @Autowired
  PasswordEncoder passwordEncoder;

  private final UserRepository userRepository;

  public Application(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostConstruct
  public void init(){
    User user=new User();

    user.setUsername("admin");

    user.setPassword(passwordEncoder.encode("admin"));
    user.setEmail("admin@gmail.com");
    user.setPhoneNumber("123456789");
    user.setRole(Role.ADMIN);

    userRepository.save(user);



  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
