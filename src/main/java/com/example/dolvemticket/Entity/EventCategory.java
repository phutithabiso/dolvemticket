package com.example.dolvemticket.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event_categories")
@Data
@NoArgsConstructor
public class EventCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @NotBlank(message = "Categoty name is required")
    @Size(min =2, max = 100,
            message = "Category name must be between 2 and 100 character")
    @Column(nullable = false, unique = true, length = 100)
    private  String categoryName;

    @Column(columnDefinition = "TEXT")
     private String description;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy ="category", fetch = FetchType.LAZY)
    private List<Event> events = new ArrayList<>();

    @Version
    private Long version;


}
