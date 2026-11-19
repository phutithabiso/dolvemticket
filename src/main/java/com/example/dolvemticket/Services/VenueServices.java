package com.example.dolvemticket.Services;

import com.example.dolvemticket.DTO.EventRequest;
import com.example.dolvemticket.DTO.VenueRequest;
import com.example.dolvemticket.DTO.VenueResponse;
import com.example.dolvemticket.Entity.Venue;
import com.example.dolvemticket.Repository.VenueRespository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VenueServices {
    private final VenueRespository repo;
     public VenueResponse createVenue(VenueResponse request){

         Venue venue = new Venue();
         venue.setName(request.name());
         venue.setAddress(request.address());
         venue.setCity(request.city());
         venue.setCountry(request.country());
         Venue saveVenue = repo.save(venue);

         return mapToResponse(saveVenue);
     }

     public  VenueResponse updateVenue(VenueResponse request){
          Venue ven = repo.findById(request.venueId()).orElseThrow(() -> new RuntimeException("Venue not found"));
         Venue venue = new Venue();
         venue.setName(request.name());
         venue.setAddress(request.address());
         venue.setCity(request.city());
         venue.setCountry(request.country());
         Venue saveVenue = repo.save(venue);

         return mapToResponse(saveVenue);

     }
     public VenueResponse mapToResponse(Venue venue){
         return new VenueResponse(
                 venue.getVenueId(),
                 venue.getName(),
                 venue.getAddress(),
                 venue.getCity(),
                 venue.getCountry(),
                 venue.getCapacity(),
                 venue.getCreatedAt(),
                 venue.getUpdatedAt()


         );
     }
}
