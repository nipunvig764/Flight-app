package com.flightBackend.flight.backend.entity;

import com.flightBackend.flight.backend.configurations.Role;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
public class UserDetail{
    private String userId;
    private String city;
    @Id
    private String email;
    private String name;
    private String password;
    private String phone;
    @Enumerated(value = EnumType.STRING)
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

    // @Override
// public Collection<? extends GrantedAuthority> getAuthorities() {
//    return List.of(new SimpleGrantedAuthority(role.name()));
// }
//
    public String getPassword() {
        return password;
    }

// @Override
// public String getUsername() {
//    return email;
// }
//
// @Override
// public boolean isAccountNonExpired() {
//    return true;
// }
//
// @Override
// public boolean isAccountNonLocked() {
//    return true;
// }
//
// @Override
// public boolean isCredentialsNonExpired() {
//    return true;
// }
//
// @Override
// public boolean isEnabled() {
//    return true;
// }

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
