package com.booking.service;

import java.util.List;

import com.booking.dto.BookingDTO;
import com.booking.model.Booking;

public interface BookingService {
	
	BookingDTO createBooking(BookingDTO bookingDTO);
    BookingDTO updateBooking(BookingDTO bookingDTO);
    void deleteBooking(Long id);
   // BookingDetailsResponseDTO getBookingDetails(Long id);
    List<BookingDTO> getBookingsByCustomer(Long customerId);
    List<Booking> getAllBookings();


}
