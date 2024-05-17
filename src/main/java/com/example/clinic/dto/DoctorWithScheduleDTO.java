package com.example.clinic.dto;

import com.example.clinic.entity.DoctorSchedule;
import com.example.clinic.entity.Speciality;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class DoctorWithScheduleDTO {

    private Integer id;
    private String name;
    private String description;
    private String position;
    private String image;
    private Double examination_Price;
    private String examination_Address;
    private Speciality speciality;
    private List<DoctorSchedule> doctorSchedules;

}
