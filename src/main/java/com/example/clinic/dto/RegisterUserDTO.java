package com.example.clinic.dto;


import lombok.Data;

@Data
public class RegisterUserDTO {

    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Boolean gender;
    private String dateOfBirth;
    private String photo;

}
