package com.example.clinic.repository;

import com.example.clinic.dto.DoctorWithScheduleDTO;
import com.example.clinic.entity.Doctor;
import com.example.clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    @Query(value = "SELECT * FROM DOCTOR d LEFT JOIN DOCTOR_SCHEDULE ds ON d.id = ds.doctor_id", nativeQuery = true)
    List<DoctorWithScheduleDTO> findAllWithSchedules();

    @Query("Select c FROM Doctor c WHERE c.username = :username")
    Doctor getDoctorByUsername(@Param("username") String username);
}
