package com.flightBackend.flight.backend.services;


import com.flightBackend.flight.backend.entity.TicketDetails;
import com.flightBackend.flight.backend.repository.PassengerRepository;
import com.flightBackend.flight.backend.repository.TicketRepository;
import com.flightBackend.flight.backend.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PassengerRepository passengerRepository;


    public void sendEmail(String userId) {

        List<TicketDetails> ticketDetails = ticketRepository.ticketDetails(userId);

        ticketDetails.stream().forEach(ticketDetails1 -> {
            System.out.println(ticketDetails1);
            ticketDetails1.getPassengerDetails().stream().forEach(passengerDetails -> {
                String flightId = ticketDetails1.getFlightDetails().getFlightId();
                Date departureDate = ticketDetails1.getDepartureDate();
                String departureTime = ticketDetails1.getDepartureTime();
                String source = ticketDetails1.getFlightDetails().getSource();
                String destination = ticketDetails1.getFlightDetails().getDestination();
                Double totalFare = ticketDetails1.getTotalFare();
                String passengerName = passengerDetails.getPassengerName();
                if(passengerDetails.isEmailAcknowledgement()==false){
                    try{
                        MimeMessage message = javaMailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(message, true);

                        String subject = "Flight Booking Confirmation";
                        String body = "Dear " + ticketDetails1.getUserDetails().getUserId() + ",\n\n"
                                + "Your flight booking has been confirmed.\n"
                                + "Flight Id: " + flightId + "\n"
                                + "Departure Date: " + departureDate + "\n"
                                + "Departure Time: " + departureTime + "\n"
                                + "From : " + source + "\n"
                                + "To : " + destination + "\n"
                                + "Passenger Name : " + passengerDetails.getPassengerName() + "\n"
                                + "Thank you for choosing our airline.";
                        helper.setTo(ticketDetails1.getUserDetails().getEmail());
                        helper.setSubject(subject);
                        helper.setText(body);

                        // Create the attachment file
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        Utility.createPdf(flightId,departureDate,departureTime,source,destination,totalFare,passengerName,byteArrayOutputStream);
                        // Attach the file to the email
                        helper.addAttachment("ticket.pdf", new ByteArrayResource(byteArrayOutputStream.toByteArray()),"application/pdf");
                        // Send the email
                        javaMailSender.send(message);
                        passengerDetails.setEmailAcknowledgement(true);
                        passengerRepository.save(passengerDetails);
                    }catch (MailException | MessagingException  e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
        });


    }
}























