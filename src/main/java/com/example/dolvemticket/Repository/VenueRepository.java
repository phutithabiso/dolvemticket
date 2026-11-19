package com.example.dolvemticket.Repository;

import com.example.dolvemticket.Entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenueRepository extends JpaRepository<Venue,Long> {
    @Override
    Optional<Venue> findById(Long VenueId);
    boolean findVenueByName(String Venuename);
}
