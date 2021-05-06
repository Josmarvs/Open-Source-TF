package com.appreservas.Repository;

import com.appreservas.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IBookingRepository extends JpaRepository<Booking,Long> {

    @Query("Select b from Booking b where b.checkin_Date=:checkin and b.checkout_Date =:checkout ")
    public List<Booking> find(@Param("checkin")Date checkin_Date,
                              @Param("checkout")Date checkout_Date);


}
