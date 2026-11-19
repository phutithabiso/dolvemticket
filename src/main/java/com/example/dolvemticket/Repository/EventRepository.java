package com.example.dolvemticket.Repository;

import com.example.dolvemticket.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {
    Optional<Event> findByEventId(Long eventId);
    boolean existsByEventId(Long eventId);
    boolean existsByEventTitle(String eventTitle);
    boolean existsByEventDate(String eventDate);
    boolean existsByEventTime(String eventTime);
    boolean existsByEventLocation(String eventLocation);

}
