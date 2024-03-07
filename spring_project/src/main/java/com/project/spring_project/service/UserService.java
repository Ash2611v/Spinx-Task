package com.project.spring_project.service;


import com.project.spring_project.dao.RoleDao;
import com.project.spring_project.dao.UserDao;
import com.project.spring_project.entity.Role;
import com.project.spring_project.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean validateCredentials(String email, String password) {

        // Retrieve the user by email
        User user = userDao.findByEmail(email);

        // Check if the user exists and the password matches
        return (user!=null && passwordEncoder.matches(password, user.getPassword()));
    }

    public User registerNewUser(User user){
        if(user.getRoleName()==null){
            user.setRoleName("user");
        }
        if(user.getPassword()==null){
            user.setPassword("root@123");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    public void initRolesAndUsers(){

            Role roleAdmin=new Role();
            roleAdmin.setRoleName("admin");
            roleAdmin.setRoleDescription("admin role for the admin of application");
            this.roleDao.save(roleAdmin);

            Role roleUser=new Role();
            roleUser.setRoleName("user");
            roleUser.setRoleDescription("default role for the user of application");
            this.roleDao.save(roleUser);

            User admin =new User();
            admin.setName("admin");
        String encodedPassword = passwordEncoder.encode("admin@123");
        admin.setPassword(encodedPassword);
            admin.setEmail("admin@gmail.com");
            admin.setPhoneNumber("0123456789");
        admin.setRoleName(roleAdmin.getRoleName());


        User user =new User();
        user.setName("ashwini");
        encodedPassword = passwordEncoder.encode("ashwini@123");
        user.setPassword(encodedPassword);
        user.setEmail("ashwini@gmail.com");
        user.setPhoneNumber("0123456789");
        user.setRoleName(roleUser.getRoleName());

        this.userDao.save(admin);
        this.userDao.save(user);

    }

    public User updateUser(String email, User updatedUserData) {
        User existingUser = userDao.findByEmail(email);

        // Update the user data with the new values
        existingUser.setName(updatedUserData.getName());
        existingUser.setPhoneNumber(updatedUserData.getPhoneNumber());
        existingUser.setPassword(passwordEncoder.encode(updatedUserData.getPassword()));
        existingUser.setRoleName(updatedUserData.getRoleName()); // Update role name

        // Save the updated user data
        return userDao.save(existingUser);
    }

    public void deleteUser(String email) {
        // Check if the user exists
        User existingUser = userDao.findById(email)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + email));

        // Delete the user
        userDao.delete(existingUser);
    }


    public User[] getAllUsers() {
        List<User> userList = (List<User>) userDao.findAll();
        return userList.toArray(new User[0]);
    }

}
