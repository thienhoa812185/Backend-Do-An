package com.example.clinic.controller;


import com.example.clinic.dto.BookingResponseDTO;
import com.example.clinic.dto.DoctorDTO;
import com.example.clinic.dto.ScheduleTimeDTO;
import com.example.clinic.entity.*;
import com.example.clinic.service.*;
import org.springframework.transaction.annotation.Transactional;
import com.example.clinic.enums.RolesEnum;
import com.example.clinic.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final SpecialityService specialityService;

    private final BookingService bookingService;

    private final DoctorScheduleService doctorScheduleService;
    private final RoleService roleService;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final FileUtils fileUtils;

    @Autowired
    public DoctorController(DoctorService doctorService, FileUtils fileUtils, SpecialityService specialityService, PasswordEncoder passwordEncoder, RoleService roleService, UserService userService, DoctorScheduleService doctorScheduleService, BookingService bookingService) {
        this.doctorService = doctorService;
        this.fileUtils = fileUtils;
        this.specialityService = specialityService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userService = userService;
        this.doctorScheduleService = doctorScheduleService;
        this.bookingService = bookingService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllDoctor() {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getAll());
    }

    @GetMapping("/getAllDoctorWithSchedule")
    public ResponseEntity<?> getAllDoctorWithSchedule() {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getAllDoctorsWithSchedules());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getDoctorById(id));
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<?> getDoctorByUsername(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getDoctorByUsername(username));
    }

    @GetMapping("/getTheFiveBestDoctor/{specialityId}")
    public ResponseEntity<?> getTheFiveBestDoctor(@PathVariable Integer specialityId) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.findTop5DoctorsBySpecialityOrderByPointEvaluationDesc(specialityId));
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<?> addDoctor(@RequestParam String name, @RequestParam String description, @RequestParam String position, @RequestParam MultipartFile image, @RequestParam Double examinationPRICE, @RequestParam String examinationADDRESS, @RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String specialityName) {
        try {

            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));

            Role roleUser = roleService.getRoleByRoleName(RolesEnum.USER);
            Role roleDoctor = roleService.getRoleByRoleName(RolesEnum.DOCTOR);
            Set<Role> roles = new HashSet<>();
            roles.add(roleUser);
            roles.add(roleDoctor);
            user.setRoles(roles);

            userService.save(user);

            String pathImage = fileUtils.uploadImage(image);
            Speciality speciality = specialityService.getSpecialityByName(specialityName);
            DoctorDTO doctorDTO =
                    DoctorDTO.builder()
                            .name(name)
                            .description(description)
                            .username(username)
                            .position(position)
                            .examinationPrice(examinationPRICE)
                            .examinationAddress(examinationADDRESS)
                            .speciality(speciality)
                            .image(pathImage).build();
            doctorService.addDoctor(doctorDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Add success!!!!!!!!!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Integer id) {
        Doctor doctor = doctorService.getDoctorById(id);
//        List<BookingResponseDTO> booking = bookingService.getBookingByUsernameUser(doctor.getUsername());
//        if(booking.size()!=0){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Don't delete");
//        }
        if (doctor != null) {
            doctorService.delete(doctor);
            return ResponseEntity.status(HttpStatus.OK).body("Delete success");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor hasn't existed");
        }
    }

    @PutMapping("/updateScheduleDoctor/{id}")
    public ResponseEntity<?> updateScheduleDoctor(@PathVariable Integer id, @RequestBody List<ScheduleTimeDTO> timeOffcial) {
        // Fetch the doctor entity using the provided ID
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }

        // Create a list to hold the updated doctor schedules
        List<DoctorSchedule> doctorSchedulesOffcial = new ArrayList<>();

        // Iterate through the current schedules of the doctor
        for (DoctorSchedule doctorSchedule : doctor.getDoctorSchedules()) {
            boolean found = false;
            // Check if the current schedule time is in the provided list of ScheduleTimeDTO
            for (ScheduleTimeDTO scheduleTimeDTO : timeOffcial) {
                if (doctorSchedule.getScheduleTime().getTime().equals(scheduleTimeDTO.getTime())) {
                    found = true;
                    break;
                }
            }
            // Update the status based on whether the time was found in the list
            if (found) {
                doctorSchedule.setStatus(0);
            } else {
                doctorSchedule.setStatus(1);
            }
            // Add the updated schedule to the list
            doctorSchedulesOffcial.add(doctorSchedule);
        }

        // Update the doctor's schedules with the new list
        doctor.setDoctorSchedules(doctorSchedulesOffcial);
        // Save the updated doctor entity
        doctorService.save(doctor);

        // Return a success response
        return ResponseEntity.status(HttpStatus.OK).body("Update success!!!!!!!!!");
    }

    @PutMapping("/updateInformationDoctor/{id}")
    public ResponseEntity<?> updateInformationDoctor(@PathVariable Integer id, @RequestParam String name, @RequestParam String description, @RequestParam String education, @RequestParam String experience, @RequestParam Double price, @RequestParam String position, @RequestParam String speciality) {

        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }

        Speciality speciality1 = specialityService.getSpecialityByName(speciality);

        doctor.setName(name);
        doctor.setDescription(description);
        doctor.setEducation(education);
        doctor.setExperience(experience);
        doctor.setExamination_Price(price);
        doctor.setPosition(position);
        doctor.setSpeciality(speciality1);

        doctorService.save(doctor);
        return ResponseEntity.status(HttpStatus.OK).body("Update success!!!!!!!!!");
    }

}
