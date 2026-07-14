package com.theater.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.theater.model.Theater;
import com.theater.service.TheaterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/theaters")
@RequiredArgsConstructor
@Tag(
        name = "Theater Controller",
        description = "APIs for managing theaters"
)
public class TheaterController {

    private final TheaterService theaterService;

    @Operation(
            summary = "Create Theater",
            description = "Creates a new theater."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Theater created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid theater details"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {

        return new ResponseEntity<>(theaterService.createTheater(theater), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Theater By ID",
            description = "Returns theater details using the theater ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Theater found"),
            @ApiResponse(responseCode = "404", description = "Theater not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(
            @Parameter(description = "Theater ID", example = "1")
            @PathVariable Long id) {

        return ResponseEntity.ok(theaterService.getTheaterById(id));
    }

    @Operation(
            summary = "Get All Theaters",
            description = "Returns all theaters available in the system."
    )
    @ApiResponse(responseCode = "200", description = "Theaters retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Theater>> getAllTheaters() {

        return ResponseEntity.ok(theaterService.getAllTheaters());
    }

    @Operation(
            summary = "Update Theater",
            description = "Updates an existing theater using the theater ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Theater updated successfully"),
            @ApiResponse(responseCode = "404", description = "Theater not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Theater> updateTheater(
            @Parameter(description = "Theater ID", example = "1")
            @PathVariable Long id,
            @RequestBody Theater theater) {

        return ResponseEntity.ok(theaterService.updateTheater(id, theater));
    }

    @Operation(
            summary = "Delete Theater",
            description = "Deletes a theater using its ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Theater deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Theater not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheater(
            @Parameter(description = "Theater ID", example = "1")
            @PathVariable Long id) {

        theaterService.deleteTheater(id);

        return ResponseEntity.noContent().build();
    }
}