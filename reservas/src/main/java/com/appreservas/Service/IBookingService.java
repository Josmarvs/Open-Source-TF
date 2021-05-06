package com.appreservas.Service;

import com.appreservas.Entity.Booking;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IBookingService extends CrudService<Booking>{
    public List<Booking> find(Date checkin_Date, Date checkout_Date) throws Exception;

}
