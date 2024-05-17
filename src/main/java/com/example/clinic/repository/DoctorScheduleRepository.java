package com.example.clinic.repository;

import com.example.clinic.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Integer> {
}
