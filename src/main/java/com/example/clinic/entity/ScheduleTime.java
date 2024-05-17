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
@Table(name = "SCHEDULETIME")
public class ScheduleTime {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String time;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "scheduleTime", cascade = CascadeType.ALL)
    private List<DoctorSchedule> doctorSchedules;
}
