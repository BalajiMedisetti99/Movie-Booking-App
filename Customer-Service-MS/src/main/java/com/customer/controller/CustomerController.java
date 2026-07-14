package com.customer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.customer.dto.BookingDTO;
import com.customer.model.Customer;
import com.customer.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Tag(
        name = "Customer Controller",
        description = "APIs for managing customers and their bookings"
)
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "Get Customer by ID",
            description = "Returns customer details using the customer ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/{id}")
    public Customer getCustomerById(
            @Parameter(description = "Customer ID", example = "1")
            @PathVariable Long id) {

        return customerService.getCustomerById(id);
    }

    @Operation(
            summary = "Create Customer",
            description = "Creates a new customer."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid customer data")
    })
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @Operation(
            summary = "Get All Customers",
            description = "Returns all customers."
    )
    @ApiResponse(responseCode = "200", description = "Customers retrieved successfully")
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @Operation(
            summary = "Update Customer",
            description = "Updates an existing customer."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {

        if (customer.getId() == null) {
            throw new IllegalArgumentException("Customer ID is required for update");
        }

        return customerService.updateCustomer(customer);
    }

    @Operation(
            summary = "Delete Customer",
            description = "Deletes a customer using the customer ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @DeleteMapping("/{id}")
    public void deleteCustomer(
            @Parameter(description = "Customer ID", example = "1")
            @PathVariable Long id) {

        customerService.deleteCustomer(id);
    }

    @Operation(
            summary = "Search Customers by Name",
            description = "Returns customers matching the given name."
    )
    @ApiResponse(responseCode = "200", description = "Customers retrieved successfully")
    @GetMapping("/search")
    public List<Customer> searchCustomersByName(
            @Parameter(description = "Customer Name", example = "Balaji")
            @RequestParam String name) {

        return customerService.searchCustomersByName(name);
    }

    @Operation(
            summary = "Search Customers by Email Domain",
            description = "Returns customers belonging to a specific email domain."
    )
    @ApiResponse(responseCode = "200", description = "Customers retrieved successfully")
    @GetMapping("/email-domain")
    public List<Customer> getCustomersByEmailDomain(
            @Parameter(description = "Email Domain", example = "gmail.com")
            @RequestParam String domain) {

        return customerService.getCustomersByEmailDomain(domain);
    }

    @Operation(
            summary = "Get Customer Bookings",
            description = "Returns all bookings of a specific customer by calling the Booking Service."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/{customerId}/bookings-list")
    public List<BookingDTO> getBookingsByCustomerId(
            @Parameter(description = "Customer ID", example = "1")
            @PathVariable Long customerId) {

        return customerService.getBookingsByCustomerId(customerId);
    }
}