package com.example.clinic.controller;


import com.example.clinic.dto.SpecialityRequestDTO;
import com.example.clinic.entity.Speciality;
import com.example.clinic.service.SpecialityService;
import com.example.clinic.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/speciality")
public class SpecialityController {

    private final SpecialityService specialityService;

    private final FileUtils fileUtils;

    @Autowired
    public SpecialityController(SpecialityService specialityService, FileUtils fileUtils) {
        this.specialityService = specialityService;
        this.fileUtils = fileUtils;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllSpeciality() {
        return ResponseEntity.status(HttpStatus.OK).body(specialityService.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getSpecialityById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(specialityService.getSpecialityById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSpeciality(@RequestParam String name, @RequestParam String description, @RequestParam MultipartFile image) {
        try {
            String pathImage = fileUtils.uploadImage(image);
            SpecialityRequestDTO specialityRequestDTO =
                    SpecialityRequestDTO.builder()
                            .name(name)
                            .description(description)
                            .image(pathImage).build();
            specialityService.addSpeciality(specialityRequestDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Add success!!!!!!!!!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSpeciality(@PathVariable Integer id, @RequestParam String name, @RequestParam String description) {
        try {
            Speciality speciality = specialityService.getSpecialityById(id);
            if (speciality != null) {
                speciality.setName(name);
                speciality.setDescription(description);
                specialityService.save(speciality);
                return ResponseEntity.status(HttpStatus.OK).body("Update successful");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This speciality is not existed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSpeciality(@PathVariable Integer id) {
        Speciality speciality = specialityService.getSpecialityById(id);
        if (speciality != null) {
            specialityService.delete(speciality);
            return ResponseEntity.status(HttpStatus.OK).body("Delete success");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("speciality hasn't existed");
        }
    }


}
