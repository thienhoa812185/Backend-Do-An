package com.example.clinic.repository;

import com.example.clinic.entity.Booking;
import com.example.clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByPatient(Patient patient);
}
