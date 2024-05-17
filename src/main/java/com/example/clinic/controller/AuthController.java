package com.example.clinic.controller;


import com.example.clinic.config.JwtTokenProvider;
import com.example.clinic.dto.AuthResponseDTO;
import com.example.clinic.dto.LoginDTO;
import com.example.clinic.dto.RegisterDTO;
import com.example.clinic.dto.RegisterUserDTO;
import com.example.clinic.entity.Patient;
import com.example.clinic.entity.Role;
import com.example.clinic.entity.User;
import com.example.clinic.enums.RolesEnum;
import com.example.clinic.service.PatientService;
import com.example.clinic.service.RoleService;
import com.example.clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserService userService;

    private final PatientService patientService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder,
                          RoleService roleService,
                          UserService userService,
                          PatientService patientService) {

        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userService = userService;
        this.patientService = patientService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));


            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = JwtTokenProvider.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(new AuthResponseDTO(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO registerUserDto) {
        try {
            if (userService.existedByUsername(registerUserDto.getUsername())) {
                return new ResponseEntity<>("Username already exists!", HttpStatus.CONFLICT);
            } else {
                User user = new User();

                user.setUsername(registerUserDto.getUsername());
                user.setEmail(registerUserDto.getEmail());
                user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));

                Role role = roleService.getRoleByRoleName(RolesEnum.USER);
                Set<Role> roles = new HashSet<>();
                roles.add(role);
                user.setRoles(roles);
                userService.save(user);


                Patient patient = new Patient();
                patient.setAddress(registerUserDto.getAddress());
                patient.setEmail(registerUserDto.getEmail());
                patient.setName(registerUserDto.getName());
                patient.setGender(registerUserDto.getGender());
                patient.setImage(registerUserDto.getPhoto());
                patient.setPhone(registerUserDto.getPhone());
                patient.setDateOfBirth(registerUserDto.getDateOfBirth());
                patientService.save(patient);

                return new ResponseEntity<>("User registered success!", HttpStatus.OK);
            }
        } catch (Exception e) {
            // Nếu có lỗi, rollback giao dịch
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResponseEntity<>("Failed to register user!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registerDoctor")
    public ResponseEntity<?> registerDoctor(@RequestBody RegisterDTO registerDto) {
        if (userService.existedByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.CONFLICT);
        } else {
            User user = new User();

            user.setUsername(registerDto.getUsername());
            user.setEmail(registerDto.getEmail());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            Role roleUser = roleService.getRoleByRoleName(RolesEnum.USER);
            Role roleDoctor = roleService.getRoleByRoleName(RolesEnum.DOCTOR);
            Set<Role> roles = new HashSet<>();
            roles.add(roleUser);
            roles.add(roleDoctor);

            user.setRoles(roles);

            userService.save(user);
            return new ResponseEntity<>("Doctor registered success!", HttpStatus.OK);
        }
    }

    @GetMapping("/getRole")
    public ResponseEntity<?> getAllRole() {

        Role role = roleService.getRoleByRoleName(RolesEnum.USER);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
