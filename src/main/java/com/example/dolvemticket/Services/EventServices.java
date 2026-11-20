package com.example.dolvemticket.Services;

import com.example.dolvemticket.DTO.*;
import com.example.dolvemticket.Entity.*;
import com.example.dolvemticket.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServices {
    private final EventRepository eventRepository;
    private final EventCategoryRepository categoryRepository;
    private final VenueRepository venueRepository;
    // CREATE
    @Transactional
    public EventResponse createEvent(EventRequest request) {
        validateDates(request);
        EventCategory category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Venue venue = venueRepository.findById(request.venueId())
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        Event event = new Event();

        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setStartDate(request.startDate());
        event.setEndDate(request.endDate());
        event.setStatus(request.status());
        event.setCategory(category);
        event.setVenue(venue);

        return mapToResponse(eventRepository.save(event));
    }

    // READ ALL
    @Transactional()
    public List<EventResponse> getAllEvents() {

        return eventRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // READ ONE
    @Transactional()
    public EventResponse getEventById(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return mapToResponse(event);
    }

    // UPDATE
    @Transactional
    public EventResponse updateEvent(Long eventId, EventRequest request) {

        validateDates(request);

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        EventCategory category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Venue venue = venueRepository.findById(request.venueId())
                .orElseThrow(() -> new RuntimeException("Venue not found"));

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
    @Transactional
    public void deleteEvent(Long eventId) {

        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Event not found");
        }

        eventRepository.deleteById(eventId);
    }

    // VALIDATE DATES
    private void validateDates(EventRequest request) {

        if (!request.endDate().isAfter(request.startDate())) {
            throw new IllegalArgumentException("Event end date must be after start date"
            );
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


