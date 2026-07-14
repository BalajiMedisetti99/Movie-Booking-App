package com.theater.service;

import java.util.List;

import com.theater.model.Theater;

public interface TheaterService {
	
	 Theater createTheater(Theater theater);
	 Theater getTheaterById(Long id);
     List<Theater> getAllTheaters();
     Theater updateTheater(Long id, Theater theater);
     void deleteTheater(Long id);

}
