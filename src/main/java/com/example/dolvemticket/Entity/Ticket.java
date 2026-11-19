package com.example.dolvemticket.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "tickes ")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event  event;

    @NotBlank(message = "Ticket type is required")
    @Size(max = 50,message = " Ticket type cannot exceed 50 character")
    @Column(length = 50, nullable = false, name = "ticket_type")
    private String ticket_type;

    @NotNull(message = "Ticket is required")
    @DecimalMin(value= "0.00",message = "message cannot be negative")
    @Column(nullable = false,name ="ticket price",precision = 19,scale = 2)
    private BigDecimal ticket_price;

    @NotNull(message = "Total quantity is required")
    @Positive(message = "Ticket quantity cannot be negative")
    @Column(nullable = false,name =" ticket quantity")
    private int ticket_quantity;

    @NotNull(message = "Available quantity is required")
    @Positive(message = "Available quantity cannot be negative")
    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;

    @NotNull(message = "Sales start date is required")
    @Column(name = "sales_start", nullable = false)
    private Instant salesStart;

    @NotNull(message = "Sales end date is required")
    @Column(name = "sales_end", nullable = false)
    private Instant salesEnd;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Version
    private Long version;






}
