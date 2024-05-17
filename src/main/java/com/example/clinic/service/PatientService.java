package com.example.clinic.service;

import com.example.clinic.entity.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getAllPatient();

    Patient save(Patient patient);

    Patient getPatientById(Integer id);

    Patient getPatientByEmail(String email);

    Patient getPatientByUsername(String username);
}
