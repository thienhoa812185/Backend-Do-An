package com.example.clinic.controller;


import com.example.clinic.dto.*;
import com.example.clinic.entity.Booking;
import com.example.clinic.entity.DoctorSchedule;
import com.example.clinic.entity.Patient;
import com.example.clinic.enums.StatusBooking;
import com.example.clinic.enums.StatusPayment;
import com.example.clinic.service.BookingService;
import com.example.clinic.service.DoctorScheduleService;
import com.example.clinic.service.PatientService;
import com.example.clinic.utils.CreateContentEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;
    private final PatientService patientService;

    private final DoctorScheduleService doctorScheduleService;

    private final SimpMessagingTemplate messagingTemplate;

    private JavaMailSender mailSender;

    @Autowired
    public BookingController(BookingService bookingService, PatientService patientService, DoctorScheduleService doctorScheduleService, SimpMessagingTemplate messagingTemplate, JavaMailSender mailSender) {
        this.bookingService = bookingService;
        this.patientService = patientService;
        this.doctorScheduleService = doctorScheduleService;
        this.messagingTemplate = messagingTemplate;
        this.mailSender = mailSender;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllBooking() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBooking());
    }
    //getAllBookingAdmin

    @GetMapping("/admin")
    public ResponseEntity<?> getAllBookingAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBookingAdmin());
    }

    @GetMapping("/admin/getById/{id}")
    public ResponseEntity<?> getAllBookingAdminById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBookingAdminById(id));
    }

    @GetMapping("/getBookingByEmailUser/{username}")
    public ResponseEntity<?> getBookingByEmailUser(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingByUsernameUser(username));
    }

    @GetMapping("/getBookingByUsernameDoctor/{username}")
    public ResponseEntity<?> getBookingByUsernameDoctor(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingByUsernameDoctor(username));
    }


    @PostMapping("/addBooking")
    public ResponseEntity<?> addBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            Patient patientCurrent = patientService.getPatientById(bookingDTO.getPatientId());

            DoctorSchedule doctorScheduleCurrent = doctorScheduleService.getDoctorScheduleById(bookingDTO.getDoctorScheduleId());

            System.out.println(bookingDTO);

            Booking saveBooking = Booking.builder()
                    .note(bookingDTO.getNote())
                    .statusBooking(StatusBooking.PENDING)
                    .statusPayment(StatusPayment.UNPAID)
                    .price(bookingDTO.getPrice())
                    .appointmentTime(bookingDTO.getAppointmentTime())
                    .patient(patientCurrent)
                    .doctorSchedule(doctorScheduleCurrent)
                    .build();

            Booking booking = bookingService.save(saveBooking);

            WsMessageDTO messageDTO = WsMessageDTO.builder()
                    .message("NEW_DOCTOR_APPOINTMENT")
                    .data(booking.getId())
                    .build();
            messagingTemplate.convertAndSend("/topic/notify", messageDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Add success!!!!!!!!!");
    }

    @PostMapping("/updateStatusBooking/{idBooking}")
    public ResponseEntity<?> updateStatusBooking(@PathVariable Integer idBooking, @RequestBody Map<String, String> requestBody) {

        Booking booking = bookingService.getBookingById(idBooking);
        String bookingStatus = requestBody.get("statusBooking");

        booking.setStatusBooking(StatusBooking.valueOf(bookingStatus.toUpperCase()));
        bookingService.save(booking);

//        BookingResponseDTO bookingResponseDTO = bookingService.getAllBookingAdminById(idBooking);
//
//        String detailBooking = CreateContentEmail.createEmailContent(bookingResponseDTO);
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("thienhoa812185@gmail.com");
//        message.setTo(bookingResponseDTO.getPatient().getEmail());
//        message.setText(detailBooking);
//        message.setSubject("Medical Appointment Information");
//
//        mailSender.send(message);
        return ResponseEntity.status(HttpStatus.OK).body("Update success!!!!!!!!!");
    }

    @PostMapping("/updateConclude/{idBooking}")
    public ResponseEntity<?> updateConclude(@PathVariable Integer idBooking, @RequestBody Map<String, String> requestBody) {

        Booking booking = bookingService.getBookingById(idBooking);
        String conclude = requestBody.get("conclude");
        String precaution = requestBody.get("precaution");
        booking.setConclude(conclude);
        booking.setPrecaution(precaution);
        bookingService.save(booking);

        return ResponseEntity.status(HttpStatus.OK).body("Update success!!!!!!!!!");
    }

    @PostMapping("/updateStatusMethod/{idBooking}")
    public ResponseEntity<?> updateStatusMethod(@PathVariable Integer idBooking, @RequestBody Map<String, String> requestBody) {

        Booking booking = bookingService.getBookingById(idBooking);
        String statusMethod = requestBody.get("statusMethod");
        booking.setStatusPayment(StatusPayment.valueOf(statusMethod.toUpperCase()));
        bookingService.save(booking);

        return ResponseEntity.status(HttpStatus.OK).body("Update success!!!!!!!!!");
    }

    @PostMapping("/checkSlotAvailability")
    public ResponseEntity<?> checkSlotAvailability(@RequestBody CheckSlotAvailabilityRequestDTO request) {
        DoctorSchedule doctorSchedule = doctorScheduleService.getDoctorScheduleById(request.getDoctorScheduleId());

        Boolean isDung = bookingService.isSlotAvailable(doctorSchedule, request.getAppointmentTime());

        return ResponseEntity.status(HttpStatus.OK).body(isDung);
    }

}
