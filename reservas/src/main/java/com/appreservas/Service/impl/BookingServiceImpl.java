package com.appreservas.Service.impl;

import com.appreservas.Entity.Booking;
import com.appreservas.Repository.IBookingRepository;
import com.appreservas.Service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookingServiceImpl implements IBookingService {

    @Autowired
    private IBookingRepository bookingRepository;

    @Override
    @Transactional
    public Booking save(Booking booking) throws Exception {
        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> getAll() throws Exception {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getById(Long id) throws Exception {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> find(Date checkin_Date, Date checkout_Date) throws Exception {
        return bookingRepository.find(checkin_Date,checkout_Date);
    }
}
