package com.example.backend.repositories;

import com.example.backend.entities.Country;
import com.example.backend.entities.TransactionEvent;

import java.util.List;

public interface TransactionRepository {

    public float totalRevenue();
    public float totalRevenueDate(Integer date1, Integer date2);
    public List<Country> totalRevenueCountry();
    public List<Country> totalRevenueCountryDate(Integer date1, Integer date2);
}
