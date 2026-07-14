package com.movie.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.movie.dto.TheaterDTO;

@FeignClient(name = "Theater-Service-MS", url = "http://localhost:4444")
public interface TheaterClient {

    @GetMapping("/api/v1/theaters/{id}")
    TheaterDTO getTheaterById(@PathVariable("id") Long id);
}
