package com.example.backend.repositories;

import com.example.backend.entities.Country;
import com.example.backend.entities.GameSession;

import java.util.List;

public interface GameSessionRepository {

    public float sessionsAvg();
    public float sessionsAvgDate(Integer date1, Integer date2);
    public List<Country> sessionsAvgCountry();
    public List<Country> sessionsAvgCountryDate(Integer date1, Integer date2);

    public float timeAvg();
    public float timeAvgDate(Integer date1, Integer date2);
    public List<Country> timeAvgCountry();
    public List<Country> timeAvgCountryDate(Integer date1, Integer date2);

    public List<GameSession> userGameSessions(String user_id);
}
