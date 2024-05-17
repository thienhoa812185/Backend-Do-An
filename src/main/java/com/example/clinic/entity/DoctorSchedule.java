package com.example.clinic.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DOCTOR_SCHEDULETIME")
public class DoctorSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scheduletime_id")
    private ScheduleTime scheduleTime;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorSchedule", cascade = CascadeType.ALL)
    private List<Booking> bookings;

}
