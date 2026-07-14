package com.booking.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booking.client.CustomerClient;
import com.booking.client.MovieClient;
import com.booking.dto.BookingDTO;
import com.booking.exception.ResourceNotFoundException;
import com.booking.model.Booking;
import com.booking.repository.BookingRepository;
import com.booking.service.BookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{
	
	private final BookingRepository bookingRepository;
	private final CustomerClient customerClient;
    private final MovieClient movieClient;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        // Validate customer and movie exist
        customerClient.getCustomerById(bookingDTO.getCustomerId());
        movieClient.getMovieById(bookingDTO.getMovieId());

        Booking booking = Booking.builder()
                .customerId(bookingDTO.getCustomerId())   // set customerId
                .movieId(bookingDTO.getMovieId())         // set movieId
                .seatNumber(bookingDTO.getSeatNumber())
                .bookingDate(LocalDate.now())
                .build();

        Booking savedBooking = bookingRepository.save(booking);
        return convertToDto(savedBooking);
    }

    @Override
    public BookingDTO updateBooking(BookingDTO bookingDTO) {
        Booking existing = bookingRepository.findById(bookingDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingDTO.getId()));

        customerClient.getCustomerById(bookingDTO.getCustomerId());
        movieClient.getMovieById(bookingDTO.getMovieId());

        existing.setCustomerId(bookingDTO.getCustomerId());
        existing.setMovieId(bookingDTO.getMovieId());        
        existing.setSeatNumber(bookingDTO.getSeatNumber());
        existing.setBookingDate(LocalDate.now());

        Booking updatedBooking = bookingRepository.save(existing);
        return convertToDto(updatedBooking);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }


    @Override
    public List<BookingDTO> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    private BookingDTO convertToDto(Booking booking) {
        return BookingDTO.builder()
                .id(booking.getId())
                .customerId(booking.getCustomerId())
                .movieId(booking.getMovieId())
                .seatNumber(booking.getSeatNumber())
                .bookingDate(booking.getBookingDate())
                .build();
    }

	@Override
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

   
}