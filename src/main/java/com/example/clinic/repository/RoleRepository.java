package com.example.clinic.repository;

import com.example.clinic.entity.Role;
import com.example.clinic.enums.RolesEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(RolesEnum name);
}
