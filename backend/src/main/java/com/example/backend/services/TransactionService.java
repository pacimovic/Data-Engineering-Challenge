package com.example.backend.services;

import com.example.backend.entities.Country;
import com.example.backend.entities.TransactionEvent;
import com.example.backend.repositories.TransactionRepository;

import javax.inject.Inject;
import java.util.List;

public class TransactionService {

    @Inject
    private TransactionRepository transactionRepository;

    public float totalRevenue(){
        return transactionRepository.totalRevenue();
    }
    public float totalRevenueDate(Integer date1, Integer date2){
        return transactionRepository.totalRevenueDate(date1, date2);
    }
    public List<Country> totalRevenueCountry() {
        return this.transactionRepository.totalRevenueCountry();
    }
    public List<Country> totalRevenueCountryDate(Integer date1, Integer date2) {
        return this.transactionRepository.totalRevenueCountryDate(date1, date2);
    }
}
