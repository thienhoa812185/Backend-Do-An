package com.example.clinic.service;

import com.example.clinic.dto.SpecialityRequestDTO;
import com.example.clinic.entity.Speciality;

import java.util.List;

public interface SpecialityService {
    List<Speciality> getAll();

    Speciality addSpeciality(SpecialityRequestDTO specialityRequestDTO);

    Speciality save(Speciality speciality);

    Speciality getSpecialityById(Integer id);

    Speciality getSpecialityByName(String name);
    void delete(Speciality speciality);
}
