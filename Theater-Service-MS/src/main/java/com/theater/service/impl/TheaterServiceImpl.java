package com.theater.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.theater.exception.ResourceNotFoundException;
import com.theater.model.Theater;
import com.theater.repository.TheaterRepository;
import com.theater.service.TheaterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService{
	
	 private final TheaterRepository theaterRepository;

	    @Override
	    public Theater createTheater(Theater theater) {
	        return theaterRepository.save(theater);
	    }

	    @Override
	    public Theater getTheaterById(Long id) {
	        return theaterRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Theater not found with id: " + id));
	    }

	    @Override
	    public List<Theater> getAllTheaters() {
	        return theaterRepository.findAll();
	    }

	    @Override
	    public Theater updateTheater(Long id, Theater theater) {
	        Theater existing = getTheaterById(id);
	        existing.setName(theater.getName());
	        existing.setLocation(theater.getLocation());
	        return theaterRepository.save(existing);
	    }

	    @Override
	    public void deleteTheater(Long id) {
	        Theater theater = getTheaterById(id);
	        theaterRepository.delete(theater);
	    }

}
