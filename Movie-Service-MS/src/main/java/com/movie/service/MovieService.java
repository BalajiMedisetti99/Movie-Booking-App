package com.movie.service;

import java.util.List;

import com.movie.dto.TheaterDTO;
import com.movie.model.Movie;

public interface MovieService {
	
	Movie createMovie(Movie movie);
    Movie updateMovie(Long id, Movie movie);
    void deleteMovie(Long id);
    Movie getMovieById(Long id);
    List<Movie> getAllMovies();
    TheaterDTO getMovieTheater(Long movieId);

}
