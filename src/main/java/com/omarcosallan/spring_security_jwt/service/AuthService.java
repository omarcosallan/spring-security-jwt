package com.omarcosallan.spring_security_jwt.service;

import com.omarcosallan.spring_security_jwt.dto.LoginDTO;
import com.omarcosallan.spring_security_jwt.dto.RegisterDTO;
import com.omarcosallan.spring_security_jwt.entity.User;
import com.omarcosallan.spring_security_jwt.entity.enums.Role;
import com.omarcosallan.spring_security_jwt.repository.UserRepository;
import com.omarcosallan.spring_security_jwt.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public User save(RegisterDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("O e-mail " + dto.email() + " já está em uso.");
        }

        User user = new User();
        user.setName(dto.name());
        user.setUsername(dto.email());
        user.setEmail(dto.email());
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        user.setPassword(encryptedPassword);
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public String login(LoginDTO dto) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return tokenService.generateToken(userDetails, dto.email());
    }
}
