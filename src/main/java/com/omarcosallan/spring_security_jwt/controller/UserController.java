package com.omarcosallan.spring_security_jwt.controller;

import com.omarcosallan.spring_security_jwt.entity.dto.UserResponseDTO;
import com.omarcosallan.spring_security_jwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }
}
