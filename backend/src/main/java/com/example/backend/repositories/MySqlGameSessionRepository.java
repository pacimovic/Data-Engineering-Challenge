package com.example.backend.repositories;

import com.example.backend.entities.GameSession;
import com.example.backend.entities.LogInOut;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlGameSessionRepository extends MySqlAbstractRepository implements GameSessionRepository{

    @Override
    public List<GameSession> userGameSessions(String user_id) {
        List<GameSession> userGameSessions = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM sessions WHERE user_id = ?");
            preparedStatement.setString(1,user_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int session_id = resultSet.getInt("sessionId");
                int logInTime = resultSet.getInt("logInTime");
                int logOutTime = resultSet.getInt("logOutTime");
                String user_session_id = resultSet.getString("user_id");
                GameSession gameSession = new GameSession(session_id, user_session_id, logInTime, logOutTime);
                userGameSessions.add(gameSession);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return userGameSessions;
    }
}
