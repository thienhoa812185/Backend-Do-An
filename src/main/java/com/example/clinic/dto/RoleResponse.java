package com.example.clinic.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleResponse {

    private Integer Id;
    private List<String> role_Name;
}
