package com.example.clinic.service;

import com.example.clinic.dto.DoctorDTO;
import com.example.clinic.dto.DoctorWithScheduleDTO;
import com.example.clinic.dto.SpecialityRequestDTO;
import com.example.clinic.entity.Doctor;
import com.example.clinic.entity.Speciality;

import java.util.List;

public interface DoctorService {

    List<Doctor> getAll();

    List<DoctorWithScheduleDTO> getAllDoctorsWithSchedules();

    Doctor save (Doctor doctor);
    Doctor getDoctorById(Integer id);
    Doctor getDoctorByUsername(String username);
    Doctor addDoctor(DoctorDTO doctor);
    List<Doctor> findTop5DoctorsBySpecialityOrderByPointEvaluationDesc(Integer specialityId);

    void delete(Doctor doctor);
}
