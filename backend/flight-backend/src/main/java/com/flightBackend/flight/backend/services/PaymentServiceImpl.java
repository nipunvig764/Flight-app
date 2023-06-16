package com.flightBackend.flight.backend.services;

import java.util.Optional;

import com.flightBackend.flight.backend.dto.CreditCardDetailsDTO;
import com.flightBackend.flight.backend.entity.CreditCardDetails;
import com.flightBackend.flight.backend.exceptions.CreditCardException;
import com.flightBackend.flight.backend.exceptions.InfyGoConstants;
import com.flightBackend.flight.backend.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


@Service("paymentService")
@PropertySource("classpath:ValidationMessages.properties")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private Environment environment;

    @Override
    public CreditCardDetailsDTO addCard(CreditCardDetailsDTO creditCardDetailsDTO) throws CreditCardException {
        String cardNumber = creditCardDetailsDTO.getCardNumber();
        Optional<CreditCardDetails> card = paymentRepository.findById(cardNumber);
        if (card.isEmpty()) {
            CreditCardDetails mappedResult = modelMapper.map(creditCardDetailsDTO, CreditCardDetails.class);
            CreditCardDetails savedCard = paymentRepository
                    .saveAndFlush(mappedResult);
            return (modelMapper.map(savedCard, CreditCardDetailsDTO.class));
        } else {
            throw new CreditCardException(environment.getProperty(InfyGoConstants.CARD_ALREADY_EXISTS.toString()));
        }
    }

    @Override
    public String payment(CreditCardDetailsDTO creditCardDetailsDTO) throws CreditCardException {
        CreditCardDetails card = paymentRepository.findCard(creditCardDetailsDTO.getCardNumber(),
                creditCardDetailsDTO.getApin(), creditCardDetailsDTO.getCvv(), creditCardDetailsDTO.getExpiryMonth(),
                creditCardDetailsDTO.getExpiryYear());
        if (card != null) {
            creditCardDetailsDTO.setTotalBill(String.valueOf(
                    Float.parseFloat(card.getTotalBill()) + Float.parseFloat(creditCardDetailsDTO.getTotalBill())));
            int updatedRows = paymentRepository.updateTotalBill(creditCardDetailsDTO.getTotalBill(),
                    creditCardDetailsDTO.getCardNumber());
            if (updatedRows == 1) {
                return "Success";
            } else {
                throw new CreditCardException(environment.getProperty(InfyGoConstants.PAYMENT_FAILED.toString()));
            }
        } else {
            throw new CreditCardException(environment.getProperty(InfyGoConstants.WRONG_CARD_DETAILS.toString()));
        }

    }

}
