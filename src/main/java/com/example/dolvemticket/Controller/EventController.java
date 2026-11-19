
package com.example.dolvemticket.Controller;

import com.example.dolvemticket.DTO.EventRequest;
import com.example.dolvemticket.DTO.EventResponse;
import com.example.dolvemticket.Services.EventServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventServices eventServices;

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(
            @Valid @RequestBody EventRequest request) {
        EventResponse response =
                eventServices.createEvent(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        return ResponseEntity.ok(
                eventServices.getAllEvents()
        );
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponse> getEventById(
            @PathVariable Long eventId) {

        return ResponseEntity.ok(
                eventServices.getEventById(eventId)
        );
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable Long eventId,
            @Valid @RequestBody EventRequest request) {
        return ResponseEntity.ok(
                eventServices.updateEvent(eventId, request)
        );
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable Long eventId) {

        eventServices.deleteEvent(eventId);

        return ResponseEntity.noContent().build();
    }
}
