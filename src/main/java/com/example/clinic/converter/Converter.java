package com.example.clinic.converter;

import com.example.clinic.dto.UserDTO;
import com.example.clinic.entity.Role;
import com.example.clinic.entity.User;
import org.springframework.stereotype.Component;


@Component
public class Converter {

    public UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(Role::getName).toList())
                .build();
    }
}
