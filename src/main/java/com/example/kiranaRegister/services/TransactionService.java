package com.example.kiranaRegister.services;

import com.example.kiranaRegister.entities.Transaction;
import com.example.kiranaRegister.entities.TransactionDTO;
import com.example.kiranaRegister.repo.TransactionRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Transactional
    public void recordTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = convertDTOToEntity(transactionDTO);
        transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findByTransactionDate(LocalDate.now());
        return convertEntitiesToDTOs(transactions);
    }

    @Transactional(readOnly = true)
    public TransactionDTO getTransactionById(Long id){
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(this::convertEntityToDTO).orElse(null);
    }

    private Transaction convertDTOToEntity(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(transactionDTO.getType());
        transaction.setTransactionTime(LocalDateTime.now());
        return transaction;
    }

    private TransactionDTO convertEntityToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setAmount(transaction.getAmount());
        dto.setType(transaction.getType());
        return dto;
    }

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    private List<TransactionDTO> convertEntitiesToDTOs(List<Transaction> transactions) {
        List<TransactionDTO> transactionDTOs = new ArrayList<>();

        for (Transaction transaction : transactions) {
            TransactionDTO dto = new TransactionDTO();
            dto.setAmount(transaction.getAmount());
            dto.setType(transaction.getType());
            transactionDTOs.add(dto);
        }

        return transactionDTOs;
    }

}
