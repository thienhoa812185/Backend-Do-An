package com.example.clinic.service.impl;

import com.example.clinic.dto.DoctorDTO;
import com.example.clinic.dto.DoctorWithScheduleDTO;
import com.example.clinic.entity.Doctor;
import com.example.clinic.entity.DoctorSchedule;
import com.example.clinic.entity.ScheduleTime;
import com.example.clinic.entity.Speciality;
import com.example.clinic.repository.DoctorRepository;
import com.example.clinic.repository.ScheduleTimeRepository;
import com.example.clinic.repository.SpecialityRepository;
import com.example.clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final ScheduleTimeRepository scheduleTimeRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, ScheduleTimeRepository scheduleTimeRepository) {
        this.doctorRepository = doctorRepository;
        this.scheduleTimeRepository = scheduleTimeRepository;
    }

    @Override
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @Override
    public List<DoctorWithScheduleDTO> getAllDoctorsWithSchedules() {
        return doctorRepository.findAllWithSchedules();
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorByUsername(String username) {
        return doctorRepository.getDoctorByUsername(username);
    }

    @Override
    public Doctor addDoctor(DoctorDTO doctor) {

        Doctor doctorOfficial = Doctor
                .builder()
                .name(doctor.getName())
                .description(doctor.getDescription())
                .position(doctor.getPosition())
                .image(doctor.getImage())
                .examination_Price(doctor.getExaminationPrice())
                .examination_Address(doctor.getExaminationAddress())
                .username(doctor.getUsername())
                .speciality(doctor.getSpeciality())
                .build();

        doctorRepository.save(doctorOfficial);

        List<ScheduleTime> scheduleTimes = scheduleTimeRepository.findAll();
        List<DoctorSchedule> doctorSchedules = new ArrayList<>();
        for (ScheduleTime scheduleTime : scheduleTimes) {
            DoctorSchedule doctorSchedule = DoctorSchedule
                    .builder()
                    .scheduleTime(scheduleTime)
                    .status(1)
                    .doctor(doctorOfficial)
                    .build();
            doctorSchedules.add(doctorSchedule);
        }

        doctorOfficial.setDoctorSchedules(doctorSchedules);
        return doctorRepository.save(doctorOfficial);
    }


    @Override
    public Doctor getDoctorById(Integer id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        return doctor.orElse(null);
    }


    @Override
    public void delete(Doctor doctor) {
        doctorRepository.delete(doctor);
    }
}
