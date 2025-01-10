package com.omarcosallan.spring_security_jwt.controllers;

import com.omarcosallan.spring_security_jwt.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/picture")
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {
        URI uri = imageService.uploadProfilePicture(file);
        return ResponseEntity.created(uri).build();
    }
}
