package com.example.dolvemticket.Repository;

import com.example.dolvemticket.Entity.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventCategoryRepository  extends JpaRepository<EventCategory,Long> {
    @Override
    Optional<EventCategory> findById(Long categoryId);
    EventCategory findByName(String name);
}
