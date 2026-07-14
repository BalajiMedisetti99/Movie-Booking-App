package com.booking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.booking.dto.BookingDTO;
import com.booking.model.Booking;
import com.booking.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/bookings")
@RequiredArgsConstructor
@Tag(
        name = "Booking Controller",
        description = "APIs for managing movie ticket bookings"
)
public class BookingController {

    private final BookingService bookingService;

    @Operation(
            summary = "Create Booking",
            description = "Creates a new movie ticket booking."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Booking created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid booking request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update Booking",
            description = "Updates an existing booking."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booking updated successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @PutMapping
    public ResponseEntity<BookingDTO> updateBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO updatedBooking = bookingService.updateBooking(bookingDTO);
        return ResponseEntity.ok(updatedBooking);
    }

    @Operation(
            summary = "Delete Booking",
            description = "Deletes a booking using its ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Booking deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(
            @Parameter(description = "Booking ID", example = "1")
            @PathVariable Long id) {

        bookingService.deleteBooking(id);
    }

    @Operation(
            summary = "Get Customer Bookings",
            description = "Returns all bookings of a specific customer."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/{customerId}")
    public List<BookingDTO> getBookingsByCustomer(
            @Parameter(description = "Customer ID", example = "101")
            @PathVariable Long customerId) {

        return bookingService.getBookingsByCustomer(customerId);
    }

    @Operation(
            summary = "Get All Bookings",
            description = "Returns all bookings available in the system."
    )
    @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully")
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}