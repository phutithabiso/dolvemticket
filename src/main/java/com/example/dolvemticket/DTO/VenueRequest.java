package com.example.dolvemticket.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record VenueRequest(

        @NotBlank(message ="Venue name is required" )
        @Size(min = 2, max = 50)
        @Column(nullable = false, length = 100)
        String name,

        @NotBlank(message = "Venue is required")
        @Column(nullable = false, columnDefinition = "TEXT")
        String address,

        @NotBlank(message = "City is required")
        @Size( min =2 ,max = 70 , message=" city must be between 2 and 70 charactor")
        @Column(nullable = false, length = 100)
        String city,

        @Size(max = 100)
        @Column(length = 100)
        String province,

        @Size(max = 100)
        @Column(length = 100)
        String country,

        @Positive(message = "Capacity must be greater than zero")
        @Column(nullable = false)
         int capacity
) {
}
