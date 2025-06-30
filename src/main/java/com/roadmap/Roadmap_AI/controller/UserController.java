package com.roadmap.Roadmap_AI.controller;

import com.roadmap.Roadmap_AI.entity.User;
import com.roadmap.Roadmap_AI.service.JwtService;
import com.roadmap.Roadmap_AI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> req) {
        User user = userService.signup(req.get("name"), req.get("email"), req.get("password"));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        String token = userService.login(req.get("email"), req.get("password"), jwtService);
        return ResponseEntity.ok(Map.of("token", token));
    }


}
