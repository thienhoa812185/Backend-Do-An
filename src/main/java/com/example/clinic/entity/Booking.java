package com.example.clinic.entity;

import com.example.clinic.enums.StatusBooking;
import com.example.clinic.enums.StatusPayment;
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

    @Column(name = "status_payment")
    @Enumerated(EnumType.STRING)
    private StatusPayment statusPayment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_scheduletime_id")
    private DoctorSchedule doctorSchedule;
}
