package com.example.clinic.controller;

import com.example.clinic.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllPatient() {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getAllPatient());
    }

    @GetMapping("/getPatientById/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientById(id));
    }

    @GetMapping("/getPatientByEmail/{email}")
    public ResponseEntity<?> getPatientByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientByEmail(email));
    }

    @GetMapping("/getPatientByUsername/{username}")
    public ResponseEntity<?> getPatientByUsername(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientByUsername(username));
    }


}
