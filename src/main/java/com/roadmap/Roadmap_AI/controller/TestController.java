package com.roadmap.Roadmap_AI.controller;

import com.roadmap.Roadmap_AI.entity.User;
import com.roadmap.Roadmap_AI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
