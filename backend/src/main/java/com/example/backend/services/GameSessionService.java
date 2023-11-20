package com.example.backend.services;

import com.example.backend.entities.Country;
import com.example.backend.entities.GameSession;
import com.example.backend.repositories.GameSessionRepository;

import javax.inject.Inject;
import java.util.List;

public class GameSessionService {

    @Inject
    private GameSessionRepository gameSessionRepository;

    public float timeAvg(){
        return this.gameSessionRepository.timeAvg();
    }
    public float timeAvgDate(Integer date1, Integer date2){
        return this.gameSessionRepository.timeAvgDate(date1, date2);
    }
    public List<Country> timeAvgCountry(){
        return this.gameSessionRepository.timeAvgCountry();
    }
    public List<Country> timeAvgCountryDate(Integer date1, Integer date2){
        return this.gameSessionRepository.timeAvgCountryDate(date1, date2);
    }

    public float sessionsAvg(){
        return this.gameSessionRepository.sessionsAvg();
    }
    public float sessionsAvgDate(Integer date1, Integer date2){
        return this.gameSessionRepository.sessionsAvgDate(date1, date2);
    }
    public List<Country> sessionsAvgCountry() {
        return this.gameSessionRepository.sessionsAvgCountry();
    }
    public List<Country> sessionsAvgCountryDate(Integer date1, Integer date2) {
        return this.gameSessionRepository.sessionsAvgCountryDate(date1, date2);
    }
    public List<GameSession> userGameSessions(String user_id){
        return this.gameSessionRepository.userGameSessions(user_id);
    }
}
