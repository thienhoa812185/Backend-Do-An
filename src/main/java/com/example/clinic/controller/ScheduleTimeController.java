package com.example.clinic.controller;


import com.example.clinic.service.ScheduleTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scheduletime")
public class ScheduleTimeController {
    private final ScheduleTimeService scheduleTimeService;

    public ScheduleTimeController(ScheduleTimeService scheduleTimeService) {
        this.scheduleTimeService = scheduleTimeService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllScheduleTime() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleTimeService.getAllScheduleTime());
    }
}
