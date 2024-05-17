package com.example.clinic.repository;

import com.example.clinic.entity.ScheduleTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleTimeRepository extends JpaRepository<ScheduleTime, Integer> {
}
