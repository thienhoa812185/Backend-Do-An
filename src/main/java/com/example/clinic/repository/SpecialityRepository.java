package com.example.clinic.repository;

import com.example.clinic.entity.Doctor;
import com.example.clinic.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {

    @Query("Select c FROM Speciality c WHERE c.name = :name")
    Speciality getSpecialityByName(@Param("name") String name);
}
