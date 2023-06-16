package com.flightBackend.flight.backend.controller;

import com.flightBackend.flight.backend.dto.CreditCardDetailsDTO;
import com.flightBackend.flight.backend.dto.SuccessMessageDTO;
import com.flightBackend.flight.backend.exceptions.CreditCardException;
import com.flightBackend.flight.backend.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/admin/add")
    public ResponseEntity<CreditCardDetailsDTO> add(@RequestBody CreditCardDetailsDTO cardDetailsDTO)
            throws CreditCardException {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.addCard(cardDetailsDTO));
    }

    @PostMapping("/pay")
    public ResponseEntity<SuccessMessageDTO> payment(@RequestBody CreditCardDetailsDTO cardDetailsDTO)
            throws CreditCardException {
        SuccessMessageDTO successMessageDTO = new SuccessMessageDTO();
        successMessageDTO.setMessage(paymentService.payment(cardDetailsDTO));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(successMessageDTO);
    }

}



