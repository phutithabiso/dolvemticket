
package com.example.dolvemticket.Services;

import com.example.dolvemticket.DTO.TicketRequest;
import com.example.dolvemticket.DTO.TicketResponse;
import com.example.dolvemticket.Entity.Event;
import com.example.dolvemticket.Entity.Ticket;
import com.example.dolvemticket.Repository.EventRepository;
import com.example.dolvemticket.Repository.TicketRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketServices {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    // CREATE TICKET
    public TicketResponse createTicket(TicketRequest request) {

        validateSalesDates(request);

        Event event = eventRepository.findById(request.eventId())
                .orElseThrow(() ->
                        new RuntimeException("Event not found"));

        Ticket ticket = new Ticket();

        ticket.setEvent(event);
        ticket.setTicketType(request.ticketType());
        ticket.setTicketPrice(request.ticketPrice());
        ticket.setTicketQuantity(request.ticketQuantity());
        ticket.setAvailableQuantity(request.ticketQuantity());
        ticket.setSalesStart(request.salesStart());
        ticket.setSalesEnd(request.salesEnd());

        ticket.setCreatedAt(Instant.now());
        ticket.setUpdatedAt(Instant.now());

        Ticket savedTicket = ticketRepository.save(ticket);

        return mapToResponse(savedTicket);
    }

    // GET ALL TICKETS
    public List<TicketResponse> getAllTickets() {

        return ticketRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET TICKET BY ID
    public TicketResponse getTicketById(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new RuntimeException("Ticket not found"));

        return mapToResponse(ticket);
    }

    // UPDATE TICKET
    public TicketResponse updateTicket(
            Long ticketId,
            TicketRequest request) {

        validateSalesDates(request);

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new RuntimeException("Ticket not found"));

        Event event = eventRepository.findById(request.eventId())
                .orElseThrow(() ->
                        new RuntimeException("Event not found"));

        int soldQuantity =
                ticket.getTicketQuantity()
                        - ticket.getAvailableQuantity();

        if (request.ticketQuantity() < soldQuantity) {
            throw new IllegalArgumentException(
                    "Quantity cannot be less than tickets already sold");
        }

        ticket.setEvent(event);
        ticket.setTicketType(request.ticketType());
        ticket.setTicketPrice(request.ticketPrice());
        ticket.setTicketQuantity(request.ticketQuantity());
        ticket.setAvailableQuantity(
                request.ticketQuantity() - soldQuantity);
        ticket.setSalesStart(request.salesStart());
        ticket.setSalesEnd(request.salesEnd());
        ticket.setUpdatedAt(Instant.now());

        Ticket updatedTicket = ticketRepository.save(ticket);

        return mapToResponse(updatedTicket);
    }

    // DELETE TICKET
    public void deleteTicket(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new RuntimeException("Ticket not found"));

        if (ticket.getTicketQuantity()
                != ticket.getAvailableQuantity()) {

            throw new IllegalStateException(
                    "Cannot delete a ticket type after tickets have been sold");
        }

        ticketRepository.delete(ticket);
    }

    // VALIDATE SALES DATES
    private void validateSalesDates(TicketRequest request) {

        if (request.salesStart() == null
                || request.salesEnd() == null) {
            throw new IllegalArgumentException(
                    "Sales start and end dates are required");
        }

        if (!request.salesEnd().isAfter(request.salesStart())) {
            throw new IllegalArgumentException(
                    "Sales end date must be after sales start date");
        }
    }

    // MAP ENTITY TO RESPONSE DTO
    private TicketResponse mapToResponse(Ticket ticket) {

        return new TicketResponse(
                ticket.getTicketId(),
                ticket.getEvent().getEventId(),
                ticket.getEvent().getTitle(),
                ticket.getTicketType(),
                ticket.getTicketPrice(),
                ticket.getTicketQuantity(),
                ticket.getAvailableQuantity(),
                ticket.getSalesStart(),
                ticket.getSalesEnd(),
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );
    }
}
