package com.customer.service;

import java.util.List;

import com.customer.dto.BookingDTO;
import com.customer.dto.CustomerBookingResponseDTO;
import com.customer.model.Customer;

public interface CustomerService {
	
	Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Long id);
    
    Customer saveCustomer(Customer customer);

    List<Customer> searchCustomersByName(String name);

    List<Customer> getCustomersByEmailDomain(String domain);

    CustomerBookingResponseDTO getCustomerWithBookings(Long customerId);
    
    List<BookingDTO> getBookingsByCustomerId(Long customerId);

}
