package com.flightBackend.flight.backend.repository;


import com.flightBackend.flight.backend.entity.TicketDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketDetails, Integer> {

    @Query(value="SELECT * FROM ticket_details t where t.user_details_email=?1",nativeQuery=true)
    public List<TicketDetails> ticketDetails(String userId);

}
