package com.omarcosallan.spring_security_jwt.services;

import com.omarcosallan.spring_security_jwt.domain.User;
import com.omarcosallan.spring_security_jwt.domain.UserDetailsImpl;
import com.omarcosallan.spring_security_jwt.exceptions.AuthorizationException;
import com.omarcosallan.spring_security_jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new AuthorizationException("Invalid credentials. Please try again."));

        return UserDetailsImpl.build(user);
    }
}
