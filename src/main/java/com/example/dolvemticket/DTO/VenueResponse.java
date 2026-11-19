package com.example.dolvemticket.DTO;

import java.time.LocalDateTime;

public record VenueResponse(
        Long venueId,
        String name,
        String address,
        String city,
        String country,
        Integer capacity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
