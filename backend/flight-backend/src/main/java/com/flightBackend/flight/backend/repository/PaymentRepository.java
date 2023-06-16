package com.flightBackend.flight.backend.repository;

import com.flightBackend.flight.backend.entity.CreditCardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PaymentRepository extends JpaRepository<CreditCardDetails,String> {

    @Query(value="SELECT * FROM credit_card_details c where c.card_number=?1 and c.apin=?2 and c.cvv=?3 and c.expiry_month=?4 and c.expiry_year=?5",nativeQuery=true)
    public CreditCardDetails findCard(String cardNumber, String apin, String cvv, String expiryMonth, String expiryYear);

    @Modifying
    @Transactional
    @Query(value="update credit_card_details set total_bill=?1 where card_number=?2",nativeQuery = true)
    public int updateTotalBill(String updatedBill,String cardNumber);
}
