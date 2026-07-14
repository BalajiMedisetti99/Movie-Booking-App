package com.customer.client;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.customer.dto.BookingDTO;
import com.customer.dto.CustomerBookingResponseDTO;

@FeignClient(name="Booking-Service-MS", url="http://localhost:1111")
public interface BookingClient {
	
	
	@GetMapping("/api/v1/{id}/bookings")
    CustomerBookingResponseDTO getCustomerWithBookings(@PathVariable("id") Long id);
	
	@GetMapping("/api/v1/{customerId}/bookings-list")
    List<BookingDTO> getBookingsByCustomerId(@PathVariable("customerId") Long customerId);

}
