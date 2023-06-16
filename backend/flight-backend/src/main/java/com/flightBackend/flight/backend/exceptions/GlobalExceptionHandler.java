package com.flightBackend.flight.backend.exceptions;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.flightBackend.flight.backend.entity.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorMessage> generalExceptionHandler(Exception e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        errorMessage.setErrorStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> constraintViolationExceptionHandler(ConstraintViolationException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", ")));
        errorMessage.setErrorStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList()).get(0));
        errorMessage.setErrorStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ErrorMessage> userNotFoundExceptionHandler(UserException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        errorMessage.setErrorStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FlightServiceException.class)
    public ResponseEntity<ErrorMessage> flightServiceExceptionHandler(FlightServiceException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        errorMessage.setErrorStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CreditCardException.class)
    public ResponseEntity<ErrorMessage> creditCardExceptionHandler(CreditCardException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        errorMessage.setErrorStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FlightBookingException.class)
    public ResponseEntity<ErrorMessage> flightBookingExceptionHandler(FlightBookingException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        errorMessage.setErrorStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
