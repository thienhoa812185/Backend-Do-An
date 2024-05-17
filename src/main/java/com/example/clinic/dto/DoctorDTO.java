package com.example.clinic.dto;


import com.example.clinic.entity.Speciality;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DoctorDTO {

    private String name;
    private String description;
    private String position;
    private Double examinationPrice;
    private String examinationAddress;
    private String username;
    private String image;
    private Speciality speciality;

}
