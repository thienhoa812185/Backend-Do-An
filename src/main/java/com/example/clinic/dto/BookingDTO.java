package com.example.clinic.dto;

import com.example.clinic.enums.StatusBooking;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingDTO {
    private String appointmentTime;
    private String note;
    private Integer patientId;
    private Integer doctorScheduleId;
}
