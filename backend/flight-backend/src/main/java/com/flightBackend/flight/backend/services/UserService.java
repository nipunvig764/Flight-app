package com.flightBackend.flight.backend.services;


import com.flightBackend.flight.backend.dto.UserDetailsDTO;
import com.flightBackend.flight.backend.entity.UserException;

public interface UserService {

    public UserDetailsDTO register(UserDetailsDTO userDTO) throws UserException;

    public UserDetailsDTO isValidLogin(String emailId, String password) throws UserException;

    public UserDetailsDTO updateUserPassword(String password, String emailId, String newPassword) throws UserException;

}

