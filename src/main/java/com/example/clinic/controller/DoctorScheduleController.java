package com.example.clinic.controller;


import com.example.clinic.entity.Role;
import com.example.clinic.enums.RolesEnum;
import com.example.clinic.service.DoctorScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctorSchedule")
public class DoctorScheduleController {

    private final DoctorScheduleService doctorScheduleService;

    public DoctorScheduleController(DoctorScheduleService doctorScheduleService) {
        this.doctorScheduleService = doctorScheduleService;
    }


    @GetMapping("")
    public ResponseEntity<?> getAllDoctorSchedule() {
        return ResponseEntity.status(HttpStatus.OK).body(doctorScheduleService.getAllDoctorSchedule());
    }

    @GetMapping("/getDoctorScheduleById/{id}")
    public ResponseEntity<?> getDoctorScheduleById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorScheduleService.getDoctorScheduleById(id));
    }
}
