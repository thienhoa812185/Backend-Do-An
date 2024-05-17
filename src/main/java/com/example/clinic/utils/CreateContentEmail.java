package com.example.clinic.utils;

import com.example.clinic.dto.BookingResponseDTO;

public class CreateContentEmail {

//    public static String createEmailContent(BookingResponseDTO appointment) {
//        String content = "<p>Hello <strong>" + appointment.getPatient().getName() + "</strong>,</p>";
//        content += "<p>Your appointment details:</p>";
//        content += "<ul>";
//        content += "<li><strong>Note:</strong> " + appointment.getNote() + "</li>";
//        content += "<li><strong>Appointment Time:</strong> " + appointment.getAppointmentTime() + "</li>";
//        content += "<li><strong>Status Booking:</strong> " + appointment.getStatusBooking() + "</li>";
//        content += "</ul>";
//
//        content += "<p>Patient Information:</p>";
//        content += "<ul>";
//        content += "<li><strong>Name:</strong> " + appointment.getPatient().getName() + "</li>";
//        content += "<li><strong>Email:</strong> " + appointment.getPatient().getEmail() + "</li>";
//        content += "<li><strong>Gender:</strong> " + (appointment.getPatient().getGender() ? "Male" : "Female") + "</li>";
//        content += "<li><strong>Address:</strong> " + appointment.getPatient().getAddress() + "</li>";
//        content += "<li><strong>Phone:</strong> " + appointment.getPatient().getPhone() + "</li>";
//        content += "<li><strong>Date of Birth:</strong> " + appointment.getPatient().getDateOfBirth() + "</li>";
//        content += "</ul>";
//
//        content += "<p>Doctor Information:</p>";
//        content += "<ul>";
//        content += "<li><strong>Name:</strong> " + appointment.getDoctor().getName() + "</li>";
//        content += "<li><strong>Description:</strong> " + appointment.getDoctor().getDescription() + "</li>";
//        content += "<li><strong>Position:</strong> " + appointment.getDoctor().getPosition() + "</li>";
//        content += "<li><strong>Examination Price:</strong> " + appointment.getDoctor().getExamination_Price() + "</li>";
//        content += "<li><strong>Examination Address:</strong> " + appointment.getDoctor().getExamination_Address() + "</li>";
//        content += "<li><strong>Speciality:</strong> " + appointment.getDoctor().getSpeciality().getName() + "</li>";
//        content += "</ul>";
//
//        content += "<p>Thank you for choosing our services!</p>";
//        content += "<p>Best regards,<br>Your Healthcare Team</p>";
//
//        return content;
//    }

    public static String createEmailContent(BookingResponseDTO appointment) {
        String content = "Hello " + appointment.getPatient().getName() + ",\n\n";
        content += "Your appointment details:\n";
        content += "Note: " + appointment.getNote() + "\n";
        content += "Appointment Time: " + appointment.getAppointmentTime() + "\n";
        content += "Status Booking: " + appointment.getStatusBooking() + "\n\n";

        content += "Patient Information:\n";
        content += "Name: " + appointment.getPatient().getName() + "\n";
        content += "Email: " + appointment.getPatient().getEmail() + "\n";
        content += "Gender: " + (appointment.getPatient().getGender() ? "Male" : "Female") + "\n";
        content += "Address: " + appointment.getPatient().getAddress() + "\n";
        content += "Phone: " + appointment.getPatient().getPhone() + "\n";
        content += "Date of Birth: " + appointment.getPatient().getDateOfBirth() + "\n\n";

        content += "Doctor Information:\n";
        content += "Name: " + appointment.getDoctor().getName() + "\n";
        content += "Description: " + appointment.getDoctor().getDescription() + "\n";
        content += "Position: " + appointment.getDoctor().getPosition() + "\n";
        content += "Examination Price: " + appointment.getDoctor().getExamination_Price() + "\n";
        content += "Examination Address: " + appointment.getDoctor().getExamination_Address() + "\n";
        content += "Speciality: " + appointment.getDoctor().getSpeciality().getName() + "\n\n";

        content += "Thank you for choosing our services!\n\n";
        content += "Best regards,\n";
        content += "Your Healthcare Team";

        return content;
    }
}
