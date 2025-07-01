package com.roadmap.Roadmap_AI.controller;


import com.roadmap.Roadmap_AI.exception.ApiResponse;
import com.roadmap.Roadmap_AI.dto.GoogleUserInfo;
import com.roadmap.Roadmap_AI.entity.User;
import com.roadmap.Roadmap_AI.service.GoogleTokenVerifierService;
import com.roadmap.Roadmap_AI.service.JwtService;
import com.roadmap.Roadmap_AI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth/oauth")
public class OAuthController {

    @Autowired
    private GoogleTokenVerifierService googleTokenVerifierService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/google")
    public ResponseEntity<ApiResponse<Map<String, Object>>> handleGoogleSignIn(@RequestBody Map<String, String> body) {
        String idToken = body.get("idToken");
        System.out.println(idToken);
        GoogleUserInfo googleUser = googleTokenVerifierService.verify(idToken);

        if (googleUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>("error", "Invalid ID token", null));
        }

        // check or register user
        User user = userService.findOrCreateUserFromGoogle(googleUser);

        String jwt = jwtService.generateToken(user);

        Map<String, Object> data = Map.of(
                "token", jwt,
                "user", Map.of(
                        "id", user.getId(),
                        "name", user.getName(),
                        "email", user.getEmail()
                )
        );

        return ResponseEntity.ok(new ApiResponse<>("success", "Login successful", data));
    }
}

