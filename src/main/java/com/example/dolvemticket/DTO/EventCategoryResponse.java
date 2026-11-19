package com.example.dolvemticket.DTO;

import java.time.LocalDateTime;

    public record EventCategoryResponse(

            Long categoryId,
            String categoryName,
            String description,
            LocalDateTime createdAt,
            LocalDateTime updatedAt

    ) {
}
