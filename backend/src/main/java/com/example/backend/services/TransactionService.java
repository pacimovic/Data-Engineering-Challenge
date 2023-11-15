package com.example.backend.services;

import com.example.backend.entities.TransactionEvent;
import com.example.backend.repositories.TransactionRepository;

import javax.inject.Inject;
import java.util.List;

public class TransactionService {

    @Inject
    private TransactionRepository transactionRepository;

    public List<TransactionEvent> allTransactions(){
        return this.transactionRepository.allTransactions();
    }
}
