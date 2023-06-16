package com.flightBackend.flight.backend.repository;


import com.flightBackend.flight.backend.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserDetail, String> {
    @Query(value="SELECT * FROM user_details u where u.email=?1",nativeQuery=true)
    UserDetail findByEmail(String email);
}
