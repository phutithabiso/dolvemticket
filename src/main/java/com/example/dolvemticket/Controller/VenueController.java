package com.example.dolvemticket.Controller;

import com.example.dolvemticket.DTO.VenueRequest;
import com.example.dolvemticket.DTO.VenueResponse;
import com.example.dolvemticket.Services.VenueServices;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueServices venueServices;

    // CREATE
    @PostMapping
    public ResponseEntity<VenueResponse> createVenue(
            @Valid @RequestBody VenueRequest request) {

        VenueResponse response = venueServices.createVenue(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<VenueResponse>> getAllVenues() {

        return ResponseEntity.ok(
                venueServices.getAllVenues()
        );
    }

    // READ ONE
    @GetMapping("/{venueId}")
    public ResponseEntity<VenueResponse> getVenueById(
            @PathVariable Long venueId) {

        return ResponseEntity.ok(
                venueServices.getVenueById(venueId)
        );
    }

    // UPDATE
    @PutMapping("/{venueId}")
    public ResponseEntity<VenueResponse> updateVenue(
            @PathVariable Long venueId,
            @Valid @RequestBody VenueRequest request) {

        return ResponseEntity.ok(
                venueServices.updateVenue(venueId, request)
        );
    }

    @DeleteMapping("/{venueId}")
    public ResponseEntity<Void> deleteVenue(@PathVariable Long venueId) {
        venueServices.deleteVenue(venueId);
        return ResponseEntity.noContent().build();
    }
}