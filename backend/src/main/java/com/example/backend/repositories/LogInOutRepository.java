package com.example.backend.repositories;

import com.example.backend.entities.Country;
import com.example.backend.entities.LogInOut;

import java.util.List;

public interface LogInOutRepository {

    public int numOfActiveUsers();
    public int numOfActiveUsersDate(Integer date1, Integer date2);
    public List<Country> numOfActiveUsersCountry();
    public List<Country> numOfActiveUsersCountryDate(Integer date1, Integer date2);
    public List<LogInOut> userLogins(String user_id);


}
