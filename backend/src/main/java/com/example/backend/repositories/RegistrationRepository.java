package com.example.backend.repositories;

import com.example.backend.entities.Country;
import com.example.backend.entities.Registration;

import java.util.List;

public interface RegistrationRepository {

    public int paidUsers();
    public int paidUsersDate(Integer date1, Integer date2);
    public List<Country> paidUsersCountry();
    public List<Country> paidUsersCountryDate(Integer date1, Integer date2);


    public Registration userRegistration(String user_id);
}
