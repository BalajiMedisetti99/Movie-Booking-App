package com.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.booking.dto.MovieDTO;

@FeignClient(name = "Movie-Service-MS", url = "http://localhost:3333")
public interface MovieClient {

    @GetMapping("/api/v1/movies/{id}")
    MovieDTO getMovieById(@PathVariable("id") Long id);
}
