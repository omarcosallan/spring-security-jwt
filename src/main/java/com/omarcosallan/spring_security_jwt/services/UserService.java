package com.omarcosallan.spring_security_jwt.services;

import com.omarcosallan.spring_security_jwt.exceptions.AuthorizationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public static UserDetails authenticated() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return (UserDetails) principal;
        }
        throw new AuthorizationException("User not authenticated");
    }
}
