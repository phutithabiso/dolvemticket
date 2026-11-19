package com.example.dolvemticket.DTO;

import java.time.LocalDateTime;

    public record EventCategoryResponse(

            Long categoryId,
            String name,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt

    ) {
}
