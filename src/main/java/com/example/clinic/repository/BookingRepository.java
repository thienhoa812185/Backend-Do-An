package com.example.clinic.repository;

import com.example.clinic.entity.Booking;
import com.example.clinic.entity.DoctorSchedule;
import com.example.clinic.entity.Patient;
import com.example.clinic.enums.StatusBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByPatient(Patient patient);

    boolean existsByDoctorScheduleAndAppointmentTimeAndStatusBookingNot(DoctorSchedule doctorSchedule, String appointmentTime, StatusBooking statusBooking);

}
