package com.example.clinic.service;

import com.example.clinic.dto.BookingResponseDTO;
import com.example.clinic.entity.Booking;
import com.example.clinic.entity.DoctorSchedule;
import com.example.clinic.entity.Speciality;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBooking();

    Booking getBookingById(Integer idBooking);

    List<BookingResponseDTO> getAllBookingAdmin();

    BookingResponseDTO getAllBookingAdminById(Integer id);

    Booking save(Booking booking);

    List<BookingResponseDTO> getBookingByUsernameUser(String username);

    List<BookingResponseDTO> getBookingByUsernameDoctor(String username);

    Boolean isSlotAvailable(DoctorSchedule doctorSchedule, String appointmentTime);


}
