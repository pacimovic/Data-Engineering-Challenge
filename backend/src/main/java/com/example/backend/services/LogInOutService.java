package com.example.backend.services;

import com.example.backend.entities.Country;
import com.example.backend.entities.LogInOut;
import com.example.backend.repositories.LogInOutRepository;

import javax.inject.Inject;
import java.util.List;

public class LogInOutService {
    @Inject
    private LogInOutRepository logInOutRepository;

    public int numOfLogins() {
        return this.logInOutRepository.numOfLogins();
    }
    public int numOfLoginsDate(Integer date1, Integer date2) {
        return this.logInOutRepository.numOfLoginsDate(date1, date2);
    }
    public List<Country> numOfLoginsCountry() {
        return this.logInOutRepository.numOfLoginsCountry();
    }
    public List<Country> numOfLoginsCountryDate(Integer date1, Integer date2) {
        return this.logInOutRepository.numOfLoginsCountryDate(date1, date2);
    }

    public int numOfActiveUsers() {
        return this.logInOutRepository.numOfActiveUsers();
    }
    public int numOfActiveUsersDate(Integer date1, Integer date2) {
        return this.logInOutRepository.numOfActiveUsersDate(date1, date2);
    }
    public List<Country> numOfActiveUsersCountry() {
        return this.logInOutRepository.numOfActiveUsersCountry();
    }
    public List<Country> numOfActiveUsersCountryDate(Integer date1, Integer date2) {
        return this.logInOutRepository.numOfActiveUsersCountryDate(date1, date2);
    }
    public List<LogInOut> userLogins(String user_id) {
        return this.logInOutRepository.userLogins(user_id);
    }

}
