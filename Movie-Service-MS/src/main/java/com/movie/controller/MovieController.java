package com.movie.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.movie.dto.TheaterDTO;
import com.movie.model.Movie;
import com.movie.service.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
@Tag(
        name = "Movie Controller",
        description = "APIs for managing movies and retrieving theater information"
)

public class MovieController {

    private final MovieService movieService;

    @Operation(
            summary = "Create Movie",
            description = "Creates a new movie."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Movie created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid movie details"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.createMovie(movie), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update Movie",
            description = "Updates an existing movie using its ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movie updated successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(
            @Parameter(description = "Movie ID", example = "1")
            @PathVariable Long id,
            @RequestBody Movie movie) {

        return ResponseEntity.ok(movieService.updateMovie(id, movie));
    }

    @Operation(
            summary = "Get Movie By ID",
            description = "Returns movie details for the specified movie ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movie found"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(
            @Parameter(description = "Movie ID", example = "1")
            @PathVariable Long id) {

        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @Operation(
            summary = "Get All Movies",
            description = "Returns all movies available in the system."
    )
    @ApiResponse(responseCode = "200", description = "Movies retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @Operation(
            summary = "Get Theater Details",
            description = "Returns theater information where the specified movie is available."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Theater details retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Movie or Theater not found")
    })
    @GetMapping("/{id}/theaters")
    public ResponseEntity<TheaterDTO> getMovieTheater(
            @Parameter(description = "Movie ID", example = "1")
            @PathVariable Long id) {

        return ResponseEntity.ok(movieService.getMovieTheater(id));
    }

    @Operation(
            summary = "Delete Movie",
            description = "Deletes a movie using its ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Movie deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(
            @Parameter(description = "Movie ID", example = "1")
            @PathVariable Long id) {

        movieService.deleteMovie(id);

        return ResponseEntity.ok("Movie with ID " + id + " deleted successfully");
    }
}