package com.example.dolvemticket.DTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record TicketResponse(

        Long ticketId,
        Long eventId,
        String eventTitle,
        String ticketType,
        BigDecimal ticketPrice,
        int ticketQuantity,
        Integer availableQuantity,
        Instant salesStart,
        Instant salesEnd,
        Instant createdAt,
        Instant updatedAt

) {}