package com.example.clinic.dto;

import com.example.clinic.entity.DoctorSchedule;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class CheckSlotAvailabilityRequestDTO {
    private Integer doctorScheduleId;
    private String appointmentTime;
}
