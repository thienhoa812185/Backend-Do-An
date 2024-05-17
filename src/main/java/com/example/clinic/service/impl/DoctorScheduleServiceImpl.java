package com.example.clinic.service.impl;

import com.example.clinic.entity.DoctorSchedule;
import com.example.clinic.entity.Speciality;
import com.example.clinic.repository.DoctorScheduleRepository;
import com.example.clinic.service.DoctorScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    private final DoctorScheduleRepository doctorScheduleRepository;

    public DoctorScheduleServiceImpl(DoctorScheduleRepository doctorScheduleRepository) {
        this.doctorScheduleRepository = doctorScheduleRepository;
    }

    @Override
    public List<DoctorSchedule> getAllDoctorSchedule() {
        return doctorScheduleRepository.findAll();
    }

    @Override
    public DoctorSchedule getDoctorScheduleById(Integer id) {
        Optional<DoctorSchedule> doctorSchedule = doctorScheduleRepository.findById(id);
        return doctorSchedule.orElse(null);
    }

    @Override
    public DoctorSchedule save(DoctorSchedule doctorSchedule) {
        return doctorScheduleRepository.save(doctorSchedule);
    }
}
