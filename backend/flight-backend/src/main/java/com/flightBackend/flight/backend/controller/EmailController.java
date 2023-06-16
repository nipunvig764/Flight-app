package com.flightBackend.flight.backend.controller;

import com.flightBackend.flight.backend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {


    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;

    }


    @PostMapping("/sendEmail")
    public void sendEmailAcknowledgment(@RequestParam String userId) {
        // Send email to the user who booked the flight
        emailService.sendEmail(userId);

    }

}