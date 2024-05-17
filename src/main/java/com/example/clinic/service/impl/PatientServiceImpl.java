package com.example.clinic.service.impl;

import com.example.clinic.entity.Patient;
import com.example.clinic.entity.Speciality;
import com.example.clinic.entity.User;
import com.example.clinic.repository.PatientRepository;
import com.example.clinic.repository.UserRepository;
import com.example.clinic.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Integer id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElse(null);
    }

    @Override
    public Patient getPatientByEmail(String email) {
        return patientRepository.getPatientByEmail(email);
    }

    @Override
    public Patient getPatientByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return patientRepository.getPatientByEmail(user.getEmail());
    }
}
