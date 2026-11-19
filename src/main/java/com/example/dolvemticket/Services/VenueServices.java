package com.example.dolvemticket.Services;

import com.example.dolvemticket.DTO.VenueRequest;
import com.example.dolvemticket.DTO.VenueResponse;
import com.example.dolvemticket.Entity.Venue;
import com.example.dolvemticket.Repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServices {
    private final VenueRepository repo;
    public VenueResponse createVenue(VenueRequest request) {

        Venue venue = new Venue();
        venue.setName(request.name());
        venue.setAddress(request.address());
        venue.setCity(request.city());
        venue.setCountry(request.country());
        Venue saveVenue = repo.save(venue);
        return mapToResponse(saveVenue);
    }

    public List<VenueResponse> getAllVenues() {
        return repo.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public VenueResponse getVenueById(Long venueId) {
        Venue venue = repo.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found"));
        return mapToResponse(venue);
    }

    public VenueResponse updateVenue(Long venueId, VenueRequest request) {
        Venue venue = repo.findById(venueId)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        venue.setName(request.name());
        venue.setAddress(request.address());
        venue.setCity(request.city());
        venue.setCountry(request.country());
        Venue saveVenue = repo.save(venue);
        return mapToResponse(saveVenue);
    }

    public void deleteVenue(Long venueId) {
        Venue venue = repo.findById(venueId).orElseThrow(() ->
                        new RuntimeException("Venue not found"));
        repo.delete(venue);
    }

    public VenueResponse mapToResponse(Venue venue) {
        return new VenueResponse(
                venue.getVenueId(),
                venue.getName(),
                venue.getAddress(),
                venue.getCity(),
                venue.getCountry(),
                venue.getCapacity(),
                venue.getCreatedAt(),
                venue.getUpdatedAt()
        );
    }
}
