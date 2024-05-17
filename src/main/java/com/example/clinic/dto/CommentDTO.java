package com.example.clinic.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer doctorId;

    private Integer patientId;

    private String review;

    private Integer rate;

    private String date;
}
