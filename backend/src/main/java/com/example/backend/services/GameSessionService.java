package com.example.backend.services;

import com.example.backend.entities.GameSession;
import com.example.backend.repositories.GameSessionRepository;

import javax.inject.Inject;
import java.util.List;

public class GameSessionService {

    @Inject
    private GameSessionRepository gameSessionRepository;

    public List<GameSession> userGameSessions(String user_id){
        return this.gameSessionRepository.userGameSessions(user_id);
    }
}
