package com.omarcosallan.spring_security_jwt.controller;

import com.omarcosallan.spring_security_jwt.dto.LoginDTO;
import com.omarcosallan.spring_security_jwt.dto.RegisterDTO;
import com.omarcosallan.spring_security_jwt.entity.User;
import com.omarcosallan.spring_security_jwt.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<User> register(@RequestBody RegisterDTO dto) {
        User user = authService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO dto) {
        String token = authService.login(dto);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
