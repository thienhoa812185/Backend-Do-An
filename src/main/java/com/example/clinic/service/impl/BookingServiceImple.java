package com.example.clinic.service.impl;

import com.example.clinic.dto.BookingDTO;
import com.example.clinic.dto.BookingResponseDTO;
import com.example.clinic.entity.*;
import com.example.clinic.repository.BookingRepository;
import com.example.clinic.repository.DoctorRepository;
import com.example.clinic.repository.PatientRepository;
import com.example.clinic.repository.UserRepository;
import com.example.clinic.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookingServiceImple implements BookingService {

    private final BookingRepository bookingRepository;

    private final PatientRepository patientRepository;

    private final UserRepository userRepository;

    private final DoctorRepository doctorRepository;


    @Autowired
    public BookingServiceImple(BookingRepository bookingRepository, PatientRepository patientRepository, UserRepository userRepository, DoctorRepository doctorRepository) {
        this.bookingRepository = bookingRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(Integer idBooking) {
        Optional<Booking> booking = bookingRepository.findById(idBooking);
        return booking.orElse(null);
    }

    @Override
    public List<BookingResponseDTO> getAllBookingAdmin() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingResponseDTO> bookingResponseDTOList = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingResponseDTO bookingDTO = BookingResponseDTO
                    .builder()
                    .id(booking.getId())
                    .note(booking.getNote())
                    .appointmentTime(booking.getAppointmentTime())
                    .conclude(booking.getConclude())
                    .precaution(booking.getPrecaution())
                    .statusBooking(booking.getStatusBooking())
                    .patient(booking.getPatient())
                    .doctorSchedule(booking.getDoctorSchedule())
                    .doctor(booking.getDoctorSchedule().getDoctor())
                    .build();
            bookingResponseDTOList.add(bookingDTO);
        }
        return bookingResponseDTOList;
    }

    @Override
    public BookingResponseDTO getAllBookingAdminById(Integer id) {
        Booking booking = bookingRepository.findById(id).get();

        BookingResponseDTO bookingDTO = BookingResponseDTO
                .builder()
                .id(booking.getId())
                .note(booking.getNote())
                .appointmentTime(booking.getAppointmentTime())
                .conclude(booking.getConclude())
                .precaution(booking.getPrecaution())
                .statusBooking(booking.getStatusBooking())
                .patient(booking.getPatient())
                .doctorSchedule(booking.getDoctorSchedule())
                .doctor(booking.getDoctorSchedule().getDoctor())
                .build();
        return bookingDTO;
    }


    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<BookingResponseDTO> getBookingByUsernameUser(String username) {
        User user = userRepository.findByUsername(username);
        Patient patient = patientRepository.getPatientByEmail(user.getEmail());
        List<Booking> bookings = bookingRepository.findByPatient(patient);

        List<BookingResponseDTO> bookingResponseDTOList = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingResponseDTO bookingDTO = BookingResponseDTO
                    .builder()
                    .id(booking.getId())
                    .note(booking.getNote())
                    .appointmentTime(booking.getAppointmentTime())
                    .statusBooking(booking.getStatusBooking())
                    .patient(booking.getPatient())
                    .doctorSchedule(booking.getDoctorSchedule())
                    .doctor(booking.getDoctorSchedule().getDoctor())
                    .build();
            bookingResponseDTOList.add(bookingDTO);


        }
        return bookingResponseDTOList;
    }

    @Override
    public List<BookingResponseDTO> getBookingByUsernameDoctor(String username) {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingResponseDTO> bookingResponseDTOList = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getDoctorSchedule().getDoctor().getUsername().equals(username)) {
                BookingResponseDTO bookingDTO = BookingResponseDTO
                        .builder()
                        .id(booking.getId())
                        .note(booking.getNote())
                        .appointmentTime(booking.getAppointmentTime())
                        .statusBooking(booking.getStatusBooking())
                        .patient(booking.getPatient())
                        .doctorSchedule(booking.getDoctorSchedule())
                        .doctor(booking.getDoctorSchedule().getDoctor())
                        .build();
                bookingResponseDTOList.add(bookingDTO);
            }
        }
        return bookingResponseDTOList;
    }

}
