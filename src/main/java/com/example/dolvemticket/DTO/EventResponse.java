package com.example.dolvemticket.DTO;


import com.example.dolvemticket.Entity.EventStatus;

import java.time.LocalDateTime;
public record EventResponse(
        Long eventId,
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        EventStatus status,
        Long categoryId,
        String categoryName,
        Long venueId,
        String venueName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
