package com.example.clinic.controller;


import com.example.clinic.converter.Converter;
import com.example.clinic.dto.RegisterUserDTO;
import com.example.clinic.dto.RoleResponse;
import com.example.clinic.dto.UserUpdateDTO;
import com.example.clinic.entity.Patient;
import com.example.clinic.entity.Role;
import com.example.clinic.entity.User;
import com.example.clinic.enums.RolesEnum;
import com.example.clinic.service.PatientService;
import com.example.clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    private final PatientService patientService;

    private final Converter converter;

    @Autowired
    public UserController(UserService userService, Converter converter, PasswordEncoder passwordEncoder, PatientService patientService) {
        this.userService = userService;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
        this.patientService = patientService;
    }

    @GetMapping("/allUsers")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users != null) {
            return ResponseEntity.status(HttpStatus.OK).body(users.stream().map(converter::toUserDTO).toList());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/getRoleByUsername")
    public ResponseEntity<?> getRoleByUsername(@RequestParam String username) {
        List<Role> roles = userService.getRoleByUserName(username);
        if (roles != null) {
            return ResponseEntity.status(HttpStatus.OK).body(roles.stream().map(role -> role.getName()).toList());
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping("/updateUser")
    @Transactional
    public ResponseEntity<String> updateUser(@RequestBody UserUpdateDTO updateUser) {
        try {
            // Lấy người dùng từ username
            User user = userService.getUserByUsername(updateUser.getUsername());
            if (user == null) {
                return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
            }
            Patient patient = patientService.getPatientByEmail(user.getEmail());
            // Cập nhật thông tin người dùng
            user.setEmail(updateUser.getEmail());
            userService.save(user);

            // Lấy thông tin bệnh nhân từ email

            if (patient == null) {
                return new ResponseEntity<>("Patient not found!", HttpStatus.NOT_FOUND);
            }

            // Cập nhật thông tin bệnh nhân
            patient.setAddress(updateUser.getAddress());
            patient.setEmail(updateUser.getEmail());
            patient.setName(updateUser.getName());
            patient.setPhone(updateUser.getPhone());
            patient.setDateOfBirth(updateUser.getDateOfBirth());
            patientService.save(patient);

            return new ResponseEntity<>("User update success!", HttpStatus.OK);

        } catch (Exception e) {
            // Nếu có lỗi, rollback giao dịch
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<>("Failed to update user!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
