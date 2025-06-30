package com.roadmap.Roadmap_AI.service;


import com.roadmap.Roadmap_AI.entity.User;
import com.roadmap.Roadmap_AI.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signup(String name, String email, String password) {
        if (userRepo.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password));
        return userRepo.save(user);
    }

    public String login(String email, String password, JwtService jwtService) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtService.generateToken(user);
    }

    public List<User> getAllUser(){
       return userRepo.findAll();
    }

    public String deleteUserById(Integer id){
        if(userRepo.findById(id).isPresent()){

            userRepo.deleteById(id);
            return "User with id "+id+" deleted.";
        }else{

        return  "User not found";
        }
    }

    public User getUserByEmail(String email){
        return userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
