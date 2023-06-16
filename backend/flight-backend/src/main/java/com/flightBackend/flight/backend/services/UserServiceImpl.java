package com.flightBackend.flight.backend.services;


import java.util.Objects;
import java.util.Optional;

import com.flightBackend.flight.backend.dto.UserDetailsDTO;
import com.flightBackend.flight.backend.entity.UserDetail;
import com.flightBackend.flight.backend.entity.UserException;
import com.flightBackend.flight.backend.exceptions.InfyGoConstants;
import com.flightBackend.flight.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service("userService")
@PropertySource("classpath:ValidationMessages.properties")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private ModelMapper modelMapper;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetailsDTO register(UserDetailsDTO userDTO) throws UserException {
        String email = userDTO.getEmail();
        Optional<UserDetail> user = userRepository.findById(email);
        if (user.isEmpty()) {
            userDTO.setUserId(userDTO.getEmail().split("@")[0]);
            UserDetail savedUser = modelMapper.map(userDTO, UserDetail.class);
            savedUser.setPassword(userDTO.getPassword());
//            savedUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.saveAndFlush(savedUser);
            return (modelMapper.map(savedUser, UserDetailsDTO.class));
        } else {
            throw new UserException(environment.getProperty(InfyGoConstants.USER_ALREADY_EXISTS.toString()));
        }
    }

    @Override
    public UserDetailsDTO isValidLogin(String emailId, String password) throws UserException {
        Optional<UserDetail> opUserDetails = userRepository.findById(emailId);
        UserDetail userDetail = opUserDetails.orElseThrow(()->new UserException(environment.getProperty(InfyGoConstants.USER_NOT_FOUND.toString())));
        if (password.equals(userDetail.getPassword())) {
            return (modelMapper.map(userDetail, UserDetailsDTO.class));
        }
        throw new UserException(environment.getProperty(InfyGoConstants.WRONG_PASSWORD.toString()));
    }

    @Override
    public UserDetailsDTO updateUserPassword(String password, String emailId, String newPassword)
            throws UserException {
        UserDetailsDTO userDetailsDTO = isValidLogin(emailId, password);
        if (Objects.nonNull(userDetailsDTO)) {
            Optional<UserDetail> optionalUserDetails = userRepository.findById(emailId);
            UserDetail userDetail = optionalUserDetails.orElseThrow(() -> new UserException(
                    environment.getProperty(InfyGoConstants.FAILED_UPDATE_PASSWORD.toString())));
            userDetail.setPassword(newPassword);
            UserDetail savedUser = userRepository.saveAndFlush(userDetail);
            return modelMapper.map(savedUser, UserDetailsDTO.class);
        }
        throw new UserException(environment.getProperty(InfyGoConstants.FAILED_UPDATE_PASSWORD.toString()));
    }
}
