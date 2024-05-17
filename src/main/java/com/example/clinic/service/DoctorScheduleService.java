package com.example.clinic.service;

import com.example.clinic.entity.DoctorSchedule;

import java.util.List;

public interface DoctorScheduleService {

    List<DoctorSchedule> getAllDoctorSchedule();

    DoctorSchedule getDoctorScheduleById(Integer id);

    DoctorSchedule save(DoctorSchedule doctorSchedule);


}
