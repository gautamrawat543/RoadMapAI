package com.roadmap.Roadmap_AI.controller;

import com.roadmap.Roadmap_AI.entity.User;
import com.roadmap.Roadmap_AI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class TestController {

    @Autowired
    private UserService userService;


    @GetMapping("/all")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Integer id){
        System.out.println("Attempting to delete user with ID: " + id);
        return userService.deleteUserById(id);
    }
}
