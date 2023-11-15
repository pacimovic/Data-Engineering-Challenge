package com.example.backend.services;

import com.example.backend.entities.LogInOut;
import com.example.backend.repositories.LogInOutRepository;

import javax.inject.Inject;
import java.util.List;

public class LogInOutService {

    @Inject
    private LogInOutRepository logInOutRepository;

    public List<LogInOut> allLogInOuts() {
        return this.logInOutRepository.allLogInOuts();
    }

    public LogInOut addLogInOut(LogInOut logInOut){
        return this.logInOutRepository.addLogInOut(logInOut);
    }


}
