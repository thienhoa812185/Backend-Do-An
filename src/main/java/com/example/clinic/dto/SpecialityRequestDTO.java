package com.example.clinic.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SpecialityRequestDTO {

    private String name;
    private String description;
    private String image;

}
