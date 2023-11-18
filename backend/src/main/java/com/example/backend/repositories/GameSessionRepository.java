package com.example.backend.repositories;

import com.example.backend.entities.GameSession;

import java.util.List;

public interface GameSessionRepository {

    public List<GameSession> userGameSessions(String user_id);
}
