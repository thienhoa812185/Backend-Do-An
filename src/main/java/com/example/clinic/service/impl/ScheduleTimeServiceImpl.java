package com.example.clinic.service.impl;

import com.example.clinic.entity.ScheduleTime;
import com.example.clinic.repository.ScheduleTimeRepository;
import com.example.clinic.service.ScheduleTimeService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ScheduleTimeServiceImpl implements ScheduleTimeService {

    private final ScheduleTimeRepository scheduleTimeRepository;

    public ScheduleTimeServiceImpl(ScheduleTimeRepository scheduleTimeRepository){
        this.scheduleTimeRepository=scheduleTimeRepository;
    }
    @Override
    public List<ScheduleTime> getAllScheduleTime() {
        return scheduleTimeRepository.findAll();
    }
}
