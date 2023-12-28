package com.example.kiranaRegister.repo;
import com.example.kiranaRegister.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTransactionDate(LocalDate date);
}

