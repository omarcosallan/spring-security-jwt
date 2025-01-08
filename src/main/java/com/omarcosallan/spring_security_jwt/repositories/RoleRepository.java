package com.omarcosallan.spring_security_jwt.repositories;

import com.omarcosallan.spring_security_jwt.domain.ERole;
import com.omarcosallan.spring_security_jwt.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole eRole);

    Set<Role> findByNameIn(Set<ERole> names);
}
