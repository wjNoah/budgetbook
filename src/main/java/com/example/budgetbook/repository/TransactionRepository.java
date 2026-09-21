package com.example.budgetbook.repository;

import com.example.budgetbook.model.AppUser;
import com.example.budgetbook.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserOrderByBookingDateDesc(AppUser user);

    List<Transaction> findByDescriptionContainingIgnoreCase(String description);

}
