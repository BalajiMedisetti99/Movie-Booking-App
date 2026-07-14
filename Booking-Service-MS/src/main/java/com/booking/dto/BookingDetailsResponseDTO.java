package com.booking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDetailsResponseDTO {
	private BookingDTO booking;
    private CustomerDTO customer;
    private MovieDTO movie;

}
