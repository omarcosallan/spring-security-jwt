package com.omarcosallan.spring_security_jwt.services;

import com.omarcosallan.spring_security_jwt.domain.ERole;
import com.omarcosallan.spring_security_jwt.domain.Role;
import com.omarcosallan.spring_security_jwt.domain.User;
import com.omarcosallan.spring_security_jwt.domain.UserDetailsImpl;
import com.omarcosallan.spring_security_jwt.dto.request.SignupRequest;
import com.omarcosallan.spring_security_jwt.dto.response.JwtResponse;
import com.omarcosallan.spring_security_jwt.exceptions.ObjectAlreadyExistsException;
import com.omarcosallan.spring_security_jwt.exceptions.ObjectNotFoundException;
import com.omarcosallan.spring_security_jwt.repositories.RoleRepository;
import com.omarcosallan.spring_security_jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public JwtResponse authenticate(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Jwt jwt = jwtService.generateToken(authentication);

        return new JwtResponse(
                jwt.getTokenValue(),
                jwt.getExpiresAt(),
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getEmail()
        );
    }

    public User register(SignupRequest body) {
        userRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new ObjectAlreadyExistsException(
                    String.format("A user with the email '%s' already exists. Please use a different email address.", body.email())
            );
        });

        User user = new User();
        user.setName(body.name());
        user.setEmail(body.email());
        user.setPassword(passwordEncoder.encode(body.password()));

        Set<ERole> strRoles = body.roles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new ObjectNotFoundException("Role is not found."));
            roles.add(userRole);
        } else {
            Set<Role> availableRoles = roleRepository.findByNameIn(strRoles);
            roles.addAll(availableRoles);
        }

        user.setRoles(roles);
        userRepository.save(user);

        return user;
    }
}
