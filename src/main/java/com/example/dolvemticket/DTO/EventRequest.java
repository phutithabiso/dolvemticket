package com.example.dolvemticket.DTO;


import com.example.dolvemticket.Entity.EventStatus;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventRequest(

        @NotBlank(message = "Event title is required")
        @Size(min = 3, max = 150,
                message = "Event title must contain 3 to 150 characters")
        String title,

        String description,

        @NotNull(message = "Start date is required")
        LocalDateTime startDate,

        @NotNull(message = "End date is required")
        LocalDateTime endDate,

        @NotNull(message = "Event status is required")
        EventStatus status,

        @NotNull(message = "Category is required")
        Long categoryId,

        @NotNull(message = "Venue is required")
        Long venueId

) {}