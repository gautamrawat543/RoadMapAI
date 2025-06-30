package com.roadmap.Roadmap_AI.controller;

import com.roadmap.Roadmap_AI.dto.ApiResponse;
import com.roadmap.Roadmap_AI.entity.User;
import com.roadmap.Roadmap_AI.service.JwtService;
import com.roadmap.Roadmap_AI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            User user = userService.signup(req.get("name"), req.get("email"), req.get("password"));
            Map<String, Object> userData = Map.of(
                    "id", user.getId(),
                    "name", user.getName(),
                    "email", user.getEmail()
            );
            return ResponseEntity.ok(new ApiResponse<>("success", "User registered successfully", userData));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("error", e.getMessage(), null));

        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        try {
            String email = req.get("email");
            String password = req.get("password");

            User user = userService.getUserByEmail(email);
            String token = userService.login(email, password, jwtService);

            Map<String, Object> loginData = Map.of(
                    "id", user.getId(),
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "token", token
            );
            return ResponseEntity.ok(new ApiResponse<>("success", "Login successful", loginData));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(new ApiResponse<>("error", e.getMessage(), null));

        }
    }


}
