package com.example.clinic.repository;

import com.example.clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("Select c FROM Patient c WHERE c.email = :email")
    Patient getPatientByEmail(@Param("email") String email);



}
