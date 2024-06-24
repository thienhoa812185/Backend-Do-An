package com.example.clinic.dto;

import com.example.clinic.entity.Doctor;
import com.example.clinic.entity.DoctorSchedule;
import com.example.clinic.entity.Patient;
import com.example.clinic.enums.StatusBooking;
import com.example.clinic.enums.StatusPayment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class BookingResponseDTO {
    private Integer id;

    private String note;

    String appointmentTime;

    private Double price;

    private StatusBooking statusBooking;

    private StatusPayment statusPayment;

    private String conclude;

    private String precaution;

    private Patient patient;

    private DoctorSchedule doctorSchedule;

    private Doctor doctor;

}
