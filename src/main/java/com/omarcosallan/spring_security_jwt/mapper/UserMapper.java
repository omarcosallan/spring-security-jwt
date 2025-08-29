package com.omarcosallan.spring_security_jwt.mapper;

import com.omarcosallan.spring_security_jwt.entity.User;
import com.omarcosallan.spring_security_jwt.entity.dto.RegisterDTO;
import com.omarcosallan.spring_security_jwt.entity.dto.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterDTO dto);
    UserResponseDTO toDTO(User user);
}
