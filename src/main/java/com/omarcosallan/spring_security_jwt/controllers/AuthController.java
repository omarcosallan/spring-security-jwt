package com.omarcosallan.spring_security_jwt.controllers;

import com.omarcosallan.spring_security_jwt.domain.User;
import com.omarcosallan.spring_security_jwt.dto.request.LoginRequest;
import com.omarcosallan.spring_security_jwt.dto.request.SignupRequest;
import com.omarcosallan.spring_security_jwt.dto.response.JwtResponse;
import com.omarcosallan.spring_security_jwt.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/sign-in")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginRequest body) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(body.email(), body.password()));

        JwtResponse result = authService.authenticate(authentication);

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<User> register(@RequestBody SignupRequest body) {
        User result = authService.register(body);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
