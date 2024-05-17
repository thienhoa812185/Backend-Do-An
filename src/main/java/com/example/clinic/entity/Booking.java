package com.example.clinic.entity;

import com.example.clinic.enums.StatusBooking;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String note;

    @Column(name = "APPOINTMENT_DATE")
    private String appointmentTime;

    private String conclude;

    private String precaution;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusBooking statusBooking;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_scheduletime_id")
    private DoctorSchedule doctorSchedule;
}
