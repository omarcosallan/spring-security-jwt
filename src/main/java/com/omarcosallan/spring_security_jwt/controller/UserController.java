package com.omarcosallan.spring_security_jwt.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(new HashMap<>());
    }
}
