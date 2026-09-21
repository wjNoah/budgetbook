package com.example.budgetbook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    @NotNull
    private LocalDate bookingDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType type;


    @ManyToOne(optional = false)
    private Category category;

    @ManyToOne(optional = false)
    private AppUser user;

    @Size(max = 200)
    private String description;

}
