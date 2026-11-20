package com.example.dolvemticket.Repository;

import com.example.dolvemticket.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByEvent_EventId(Long eventId);

    boolean existsByEvent_EventId(Long eventId);
}
