package com.example.backend.repositories;

import com.example.backend.entities.LogInOut;

import java.util.List;

public interface LogInOutRepository {
    public List<LogInOut> allLogInOuts();
    public LogInOut addLogInOut(LogInOut logInOut);
    public List<LogInOut> userLogins(String user_id);


}
