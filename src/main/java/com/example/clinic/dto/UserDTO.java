package com.example.clinic.dto;

import com.example.clinic.enums.RolesEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private List<RolesEnum> roles;

}
