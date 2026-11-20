
package com.example.dolvemticket.Services;

import com.example.dolvemticket.DTO.*;
import com.example.dolvemticket.Entity.*;
import com.example.dolvemticket.Repository.*;
import com.example.dolvemticket.Exception.BadRequestException;
import com.example.dolvemticket.Exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServices {

    private final EventRepository eventRepository;
    private final EventCategoryRepository categoryRepository;
    private final VenueRepository venueRepository;

    // CREATE
    public EventResponse createEvent(EventRequest request) {

        validateDates(request);

        EventCategory category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        Venue venue = venueRepository.findById(request.venueId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Venue not found"));

        Event event = new Event();

        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setStartDate(request.startDate());
        event.setEndDate(request.endDate());
        event.setStatus(request.status());
        event.setCategory(category);
        event.setVenue(venue);

        Event savedEvent = eventRepository.save(event);

        return mapToResponse(savedEvent);
    }

    // READ ALL
    @Transactional(readOnly = true)
    public List<EventResponse> getAllEvents() {

        return eventRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // READ ONE
    @Transactional(readOnly = true)
    public EventResponse getEventById(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found"));

        return mapToResponse(event);
    }

    // UPDATE
    public EventResponse updateEvent(Long eventId, EventRequest request) {

        validateDates(request);

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found"));

        EventCategory category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category not found"));

        Venue venue = venueRepository.findById(request.venueId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Venue not found"));

        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setStartDate(request.startDate());
        event.setEndDate(request.endDate());
        event.setStatus(request.status());
        event.setCategory(category);
        event.setVenue(venue);

        return mapToResponse(eventRepository.save(event));
    }

    // DELETE
    public void deleteEvent(Long eventId) {

        if (!eventRepository.existsById(eventId)) {
            throw new ResourceNotFoundException("Event not found");
        }

        eventRepository.deleteById(eventId);
    }

    // VALIDATE DATES
    private void validateDates(EventRequest request) {

        if (request.startDate() == null || request.endDate() == null) {
            throw new BadRequestException("Start date and end date are required");
        }

        if (!request.endDate().isAfter(request.startDate())) {
            throw new BadRequestException(
                    "Event end date must be after start date");
        }
    }

    // ENTITY TO RESPONSE DTO
    private EventResponse mapToResponse(Event event) {

        return new EventResponse(
                event.getEventId(),
                event.getTitle(),
                event.getDescription(),
                event.getStartDate(),
                event.getEndDate(),
                event.getStatus(),
                event.getCategory().getCategoryId(),
                event.getCategory().getCategoryName(),
                event.getVenue().getVenueId(),
                event.getVenue().getName(),
                event.getCreatedAt(),
                event.getUpdatedAt()
        );
    }
}
