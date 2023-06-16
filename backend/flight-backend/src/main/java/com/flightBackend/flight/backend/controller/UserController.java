package com.flightBackend.flight.backend.controller;


import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import com.flightBackend.flight.backend.dto.UserDetailsDTO;
import com.flightBackend.flight.backend.entity.UserException;
import com.flightBackend.flight.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDetailsDTO> addUser(@Valid @RequestBody UserDetailsDTO userDTO) throws UserException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDetailsDTO> updateUserPassword(@RequestParam("emailId") String emailId,
                                                             @RequestParam("password") String password,
                                                             @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[#$@%-^=()&+]).{8,20}", message = "{user.invalid.password}") @RequestParam("newPassword") String newPassword)
            throws UserException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.updateUserPassword(password, emailId, newPassword));
    }

    @GetMapping("/login")
    public ResponseEntity<UserDetailsDTO> isValidLogin(@RequestParam("emailId") String emailId,
                                                       @RequestParam("password") String password) throws UserException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.isValidLogin(emailId, password));
    }
}