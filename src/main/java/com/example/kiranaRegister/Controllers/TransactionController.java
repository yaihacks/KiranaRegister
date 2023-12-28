package com.example.kiranaRegister.Controllers;

import com.example.kiranaRegister.entities.TransactionDTO;
import com.example.kiranaRegister.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/record")
    public ResponseEntity<String> recordTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            transactionService.recordTransaction(transactionDTO);
            return ResponseEntity.ok("Transaction recorded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to record transaction");
        }
    }

    @GetMapping("/display")
    public ResponseEntity<List<TransactionDTO>> displayTransactions() {
        try {
            List<TransactionDTO> transactions = transactionService.getAllTransactions();
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        try {
            TransactionDTO transactionDTO = transactionService.getTransactionById(id);
            return ResponseEntity.ok(transactionDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

