package com.customer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.customer.client.BookingClient;
import com.customer.dto.BookingDTO;
import com.customer.dto.CustomerBookingResponseDTO;
import com.customer.dto.CustomerDTO;
import com.customer.exception.ResourceNotFoundException;
import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
    private final BookingClient bookingClient;

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + id + " not found"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer existingCustomer = customerRepository.findById(customer.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer not found with id: " + customer.getId()));

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer with ID " + id + " not found");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> searchCustomersByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public List<Customer> getCustomersByEmailDomain(String domain) {
        return customerRepository.findCustomersByEmailDomain(domain);
    }

    @Override
    public CustomerBookingResponseDTO getCustomerWithBookings(Long customerId) {
        Customer customer = getCustomerById(customerId);

        List<BookingDTO> bookings = bookingClient.getBookingsByCustomerId(customerId);

        return CustomerBookingResponseDTO.builder()
                .customer(CustomerDTO.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .email(customer.getEmail())
                        .build())
                .bookings(bookings)
                .build();
    }

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<BookingDTO> getBookingsByCustomerId(Long customerId) {
	    return bookingClient.getBookingsByCustomerId(customerId);
	}

}
