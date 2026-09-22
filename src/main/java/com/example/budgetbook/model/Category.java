package com.example.budgetbook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private boolean isActive;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setType(@NotNull TransactionType type) {
        this.type = type;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public @NotNull TransactionType getType() {
        return type;
    }

    public boolean isActive() {
        return isActive;
    }

    public @NotNull String getName() {
        return name;
    }
}
