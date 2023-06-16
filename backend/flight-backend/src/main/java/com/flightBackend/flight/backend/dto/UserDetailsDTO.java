package com.flightBackend.flight.backend.dto;


import com.flightBackend.flight.backend.configurations.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserDetailsDTO {

    private String userId;
    @Pattern(regexp = "[A-Za-z]+", message = "{user.invalid.name}")
    private String name;
    @Pattern(regexp = "[A-Za-z]{3,}", message = "{user.city.invalid}")
    private String city;
    @Email(message = "{user.invalid.email.id}")
    private String email;
    @Pattern(regexp = "[6-9][0-9]{9}", message = "{user.invalid.contact.number}")
    private String phone;
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()]).{8,20}", message = "{user.invalid.password}")
    private String password;
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
