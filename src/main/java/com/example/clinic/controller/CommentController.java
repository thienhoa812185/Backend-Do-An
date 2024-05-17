package com.example.clinic.controller;


import com.example.clinic.dto.CommentDTO;
import com.example.clinic.dto.SpecialityRequestDTO;
import com.example.clinic.entity.Comment;
import com.example.clinic.entity.Doctor;
import com.example.clinic.entity.Patient;
import com.example.clinic.service.CommentService;
import com.example.clinic.service.DoctorService;
import com.example.clinic.service.PatientService;
import com.example.clinic.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    private final DoctorService doctorService;

    private final PatientService patientService;


    @Autowired
    public CommentController(CommentService commentService, DoctorService doctorService, PatientService patientService) {
        this.commentService = commentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO) {
        try {

            Patient patient = patientService.getPatientById(commentDTO.getPatientId());

            Doctor doctor = doctorService.getDoctorById(commentDTO.getDoctorId());

            Comment comment = Comment
                    .builder()
                    .description(commentDTO.getReview())
                    .pointEvaluation(commentDTO.getRate())
                    .date(commentDTO.getDate())
                    .patient(patient)
                    .doctor(doctor)
                    .build();

            commentService.save(comment);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Add success!!!!!!!!!");
    }

}
