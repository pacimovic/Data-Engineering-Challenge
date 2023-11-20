package com.example.backend.repositories;

import com.example.backend.entities.Country;
import com.example.backend.entities.GameSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlGameSessionRepository extends MySqlAbstractRepository implements GameSessionRepository{

    @Override
    public float sessionsAvg() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        float sessionsAvg = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT AVG(session_count) AS average_sessions\n" +
                    "FROM (\n" +
                    "    SELECT user_id, COUNT(*) AS session_count\n" +
                    "    FROM sessions\n" +
                    "    GROUP BY user_id\n" +
                    "    HAVING COUNT(*) > 0\n" +
                    ") AS user_sessions;");

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                sessionsAvg = resultSet.getFloat("average_sessions");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return sessionsAvg;
    }

    @Override
    public float sessionsAvgDate(Integer date1, Integer date2) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        float sessionsAvg = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT AVG(session_count) AS average_sessions\n" +
                    "FROM (\n" +
                    "    SELECT user_id, COUNT(*) AS session_count\n" +
                    "    FROM sessions WHERE logInTime >= ? AND logOutTime <= ? \n" +
                    "    GROUP BY user_id\n" +
                    "    HAVING COUNT(*) > 0\n" +
                    ") AS user_sessions;");
            preparedStatement.setInt(1, date1);
            preparedStatement.setInt(2, date2);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                sessionsAvg = resultSet.getFloat("average_sessions");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return sessionsAvg;
    }

    @Override
    public List<Country> sessionsAvgCountry() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Country> countries = new ArrayList<>();
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT r.country AS country, AVG(session_count) AS average_sessions\n" +
                    "FROM (\n" +
                    "    SELECT s.user_id, COUNT(*) AS session_count\n" +
                    "    FROM sessions s\n" +
                    "    GROUP BY s.user_id\n" +
                    "    HAVING COUNT(*) > 0\n" +
                    ") AS user_sessions\n" +
                    "JOIN registrations r ON user_sessions.user_id = r.user_id\n" +
                    "GROUP BY r.country;");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String countryName = resultSet.getString("country");
                float sessionsAvg = resultSet.getFloat("average_sessions");
                Country country = new Country(countryName, sessionsAvg);
                countries.add(country);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return countries;
    }

    @Override
    public List<Country> sessionsAvgCountryDate(Integer date1, Integer date2) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Country> countries = new ArrayList<>();
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT r.country AS country, AVG(session_count) AS average_sessions\n" +
                    "FROM (\n" +
                    "    SELECT s.user_id, COUNT(*) AS session_count\n" +
                    "    FROM sessions s WHERE s.logInTime >= ? AND s.logOutTime <= ?\n" +
                    "    GROUP BY s.user_id\n" +
                    "    HAVING COUNT(*) > 0\n" +
                    ") AS user_sessions\n" +
                    "JOIN registrations r ON user_sessions.user_id = r.user_id\n" +
                    "GROUP BY r.country;");
            preparedStatement.setInt(1, date1);
            preparedStatement.setInt(2, date2);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String countryName = resultSet.getString("country");
                float sessionsAvg = resultSet.getFloat("average_sessions");
                Country country = new Country(countryName, sessionsAvg);
                countries.add(country);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return countries;
    }

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
