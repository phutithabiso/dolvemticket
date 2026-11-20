package com.example.dolvemticket.DTO;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;

public record TicketRequest(
        @NotNull(message = "Event ID is required")
        @Positive(message = "Event ID must be positive")
        Long eventId,

        @NotBlank(message = "Ticket type is required")
        @Size(max = 50, message = "Ticket type cannot exceed 50 characters")
        String ticketType,

        @NotNull(message = "Ticket price is required")
        @DecimalMin(value = "0.00", message = "Ticket price cannot be negative")
        BigDecimal ticketPrice,

        @Positive(message = "Ticket quantity must be positive")
        int ticketQuantity,

        @NotNull(message = "Sales start date is required")
        Instant salesStart,

        @NotNull(message = "Sales end date is required")
        Instant salesEnd

) {
}
