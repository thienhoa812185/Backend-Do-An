package com.example.clinic.controller;

import com.example.clinic.dto.WsMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointment")
public class DoctorAppointmentController {

    private final SimpMessagingTemplate messagingTemplate;


    @Autowired
    public DoctorAppointmentController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/datlich")
    public ResponseEntity<?> getAppointment() {
        WsMessageDTO messageDTO = WsMessageDTO.builder()
                .message("NEW_DOCTOR_APPOINTMENT")
                .data("Mot cuoc hop da duoc tao moi")
                .build();
        messagingTemplate.convertAndSend("/topic/notify", messageDTO);
        return new ResponseEntity<>("Dcc rfdsf", HttpStatus.OK);
    }

}
