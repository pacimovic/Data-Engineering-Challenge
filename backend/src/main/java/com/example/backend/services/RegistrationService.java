package com.example.backend.services;

import com.example.backend.entities.Country;
import com.example.backend.entities.Registration;
import com.example.backend.repositories.RegistrationRepository;

import javax.inject.Inject;
import java.util.List;

public class RegistrationService {

    @Inject
    private RegistrationRepository registrationRepository;

    public int paidUsers() {
        return this.registrationRepository.paidUsers();
    }
    public int paidUsersDate(Integer date1, Integer date2){
        return this.registrationRepository.paidUsersDate(date1, date2);
    }
    public List<Country> paidUsersCountry() {
        return this.registrationRepository.paidUsersCountry();
    }
    public List<Country> paidUsersCountryDate(Integer date1, Integer date2) {
        return this.registrationRepository.paidUsersCountryDate(date1, date2);
    }
    public Registration userRegistration(String user_id) {
        return  this.registrationRepository.userRegistration(user_id);
    }
}
