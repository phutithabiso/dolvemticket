
package com.example.dolvemticket.Controller;

import com.example.dolvemticket.DTO.TicketRequest;
import com.example.dolvemticket.DTO.TicketResponse;
import com.example.dolvemticket.Services.TicketServices;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketServices ticketServices;

    // CREATE TICKET
    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(
            @Valid @RequestBody TicketRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ticketServices.createTicket(request));
    }

    // GET ALL TICKETS
    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets() {

        return ResponseEntity.ok(
                ticketServices.getAllTickets());
    }

    // GET TICKET BY ID
    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponse> getTicketById(
            @PathVariable Long ticketId) {

        return ResponseEntity.ok(
                ticketServices.getTicketById(ticketId));
    }

    // UPDATE TICKET
    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketResponse> updateTicket(
            @PathVariable Long ticketId,
            @Valid @RequestBody TicketRequest request) {

        return ResponseEntity.ok(
                ticketServices.updateTicket(ticketId, request));
    }

    // DELETE TICKET
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(
            @PathVariable Long ticketId) {

        ticketServices.deleteTicket(ticketId);

        return ResponseEntity.noContent().build();
    }
}
