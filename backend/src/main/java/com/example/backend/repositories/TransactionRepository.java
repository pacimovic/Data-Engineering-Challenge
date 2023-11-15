package com.example.backend.repositories;

import com.example.backend.entities.TransactionEvent;

import java.util.List;

public interface TransactionRepository {

    public List<TransactionEvent> allTransactions();
}
