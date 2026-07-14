package com.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.booking.dto.CustomerDTO;

@FeignClient(name = "Customer-Service-MS", url = "http://localhost:2222")
public interface CustomerClient {

    @GetMapping("/api/v1/customers/{id}")
    CustomerDTO getCustomerById(@PathVariable("id") Long id);
}
