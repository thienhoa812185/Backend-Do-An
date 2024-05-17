package com.example.clinic.repository;

import com.example.clinic.dto.RoleResponse;
import com.example.clinic.entity.Role;
import com.example.clinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findByEmail(String email);
    boolean existsByUsername(String username);

    @Query("SELECT u.roles FROM User u WHERE u.username = :username")
    List<Role> findRolesByUsername(@Param("username") String username);


}
