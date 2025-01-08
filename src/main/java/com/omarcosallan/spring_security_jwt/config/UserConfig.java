package com.omarcosallan.spring_security_jwt.config;


import com.omarcosallan.spring_security_jwt.domain.ERole;
import com.omarcosallan.spring_security_jwt.domain.Role;
import com.omarcosallan.spring_security_jwt.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Configuration
public class UserConfig implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        roleRepository.saveAll(
                Arrays.stream(ERole.values())
                        .map(roleEnum -> roleRepository.findByName(roleEnum)
                                .orElseGet(() -> roleRepository.save(new Role(roleEnum))))
                        .toList()
        );
    }
}
