package com.example.dolvemticket.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public record EventCategotyRequest(
        @NotBlank(message = "Categoty name is required")
        @Size(min =2, max = 100,
                message = "Category name must be between 2 and 100 character")
        @Column(nullable = false, unique = true, length = 100)
        String name,
        @Column(columnDefinition = "TEXT")
        String description

) {
}
