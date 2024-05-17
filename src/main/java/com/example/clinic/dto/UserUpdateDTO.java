package com.example.clinic.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {

    private String name;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String dateOfBirth;
}
