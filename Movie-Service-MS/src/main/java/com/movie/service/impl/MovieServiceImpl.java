package com.movie.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.movie.client.TheaterClient;
import com.movie.dto.TheaterDTO;
import com.movie.exception.ResourceNotFoundException;
import com.movie.model.Movie;
import com.movie.repository.MovieRepository;
import com.movie.service.MovieService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
	
	private final MovieRepository movieRepository;
    private final TheaterClient theaterClient;

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
        Movie existing = getMovieById(id);
        existing.setTitle(movie.getTitle());
        existing.setGenre(movie.getGenre());
        existing.setTheaterId(movie.getTheaterId());
        return movieRepository.save(existing);
    }

    @Override
    public void deleteMovie(Long id) {
        Movie existing = getMovieById(id);
        movieRepository.delete(existing);
    }
    
    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie with ID " + id + " not found"));
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public TheaterDTO getMovieTheater(Long movieId) {
        Movie movie = getMovieById(movieId);
        return theaterClient.getTheaterById(movie.getTheaterId());
    }


}
